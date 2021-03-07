package com.example.forum.util;

import com.example.forum.dto.PostDegreeDTO;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 协同过滤算法
 * @author 言曌
 * @date 2021/2/4 10:39 下午
 */

public class UserCF {



    /**
     * 获得推荐用户的视频ID列表
     *
     * @param userViewList 二维列表，每一行数据为一个用户的视频浏览ID列表，第一位为其用户ID
     * @param userId       推荐用户ID
     * @return
     */
    public static List<Long> getRecommendList(List<List<Long>> userViewList, Long userId) {
        /**
         * 输入用户-->视频条目  一个用户对应多个视频
         * 用户ID	视频ID集合
         *   1		14 15 17
         *   2		14 17
         *   3		15 18
         *   4		20 21 22
         */

        // 用户总量
        int N = userViewList.size();
        int[][] sparseMatrix = new int[N][N];//建立用户稀疏矩阵，用于用户相似度计算【相似度矩阵】
        Map<Long, Integer> userItemLength = new HashMap<>();//存储每一个用户对应的不同视频总数  eg: 1 3
        Map<Long, Set<Long>> itemUserCollection = new HashMap<>();//建立视频到用户的倒排表 eg: 14 1 2
        Set<Long> items = new HashSet<>();//辅助存储视频集合
        Map<Long, Integer> userID = new HashMap<>();//辅助存储每一个用户的用户ID映射
        Map<Integer, Long> idUser = new HashMap<>();//辅助存储每一个ID对应的用户映射
        for (int i = 0; i < N; i++) {//依次处理N个用户 输入数据  以空格间隔
            Long[] user_item = userViewList.get(i).toArray(new Long[userViewList.size()]);
            int length = user_item.length;
            userItemLength.put(user_item[0], length - 1);//eg: 1 3
            userID.put(user_item[0], i);//用户ID与稀疏矩阵建立对应关系
            idUser.put(i, user_item[0]);
            //建立视频--用户倒排表
            for (int j = 1; j < length; j++) {
                if (items.contains(user_item[j])) {//如果已经包含对应的视频--用户映射，直接添加对应的用户
                    itemUserCollection.get(user_item[j]).add(user_item[0]);
                } else {//否则创建对应视频--用户集合映射
                    items.add(user_item[j]);
                    itemUserCollection.put(user_item[j], new HashSet<Long>());//创建视频--用户倒排关系
                    itemUserCollection.get(user_item[j]).add(user_item[0]);
                }
            }
        }
        //计算相似度矩阵【稀疏】
        Set<Map.Entry<Long, Set<Long>>> entrySet = itemUserCollection.entrySet();
        Iterator<Map.Entry<Long, Set<Long>>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Set<Long> commonUsers = iterator.next().getValue();
            for (Long user_u : commonUsers) {
                for (Long user_v : commonUsers) {
                    if (user_u.equals(user_v)) {
                        continue;
                    }
                    sparseMatrix[userID.get(user_u)][userID.get(user_v)] += 1;//计算用户u与用户v都有正反馈的视频总数
                }
            }
        }

        // 推荐用户
        Long recommendUser = userId;

        //计算用户之间的相似度【余弦相似性】
        int recommendUserId = userID.get(recommendUser) == null ? 0 : userID.get(recommendUser) ;
        for (int j = 0; j < sparseMatrix.length; j++) {
            if (j != recommendUserId) {
//                System.out.println(idUser.get(recommendUserId) + "--" + idUser.get(j) + "相似度:" + sparseMatrix[recommendUserId][j] / Math.sqrt(userItemLength.get(idUser.get(recommendUserId)) * userItemLength.get(idUser.get(j))));
            }
        }

        //计算指定用户recommendUser的视频推荐度
        List<PostDegreeDTO> postDegreeDTOList = new ArrayList<>();
        for (Long item : items) {//遍历每一件视频
            Set<Long> users = itemUserCollection.get(item);//得到购买当前视频的所有用户集合
            if (!users.contains(recommendUser)) {//如果被推荐用户没有购买当前视频，则进行推荐度计算
                double itemRecommendDegree = 0.0;
                for (Long user : users) {
                    itemRecommendDegree += sparseMatrix[userID.get(recommendUser)][userID.get(user)] / Math.sqrt(userItemLength.get(recommendUser) * userItemLength.get(user));//推荐度计算
                }

                PostDegreeDTO postDegreeDTO = new PostDegreeDTO(item, itemRecommendDegree);
                postDegreeDTOList.add(postDegreeDTO);

                System.out.println("The item " + item + " for " + recommendUser + "'s recommended degree:" + itemRecommendDegree);
            }
        }
        postDegreeDTOList.sort(Collections.reverseOrder());
        return postDegreeDTOList.stream().map(p -> p.getPostId()).collect(Collectors.toList());

    }


}
