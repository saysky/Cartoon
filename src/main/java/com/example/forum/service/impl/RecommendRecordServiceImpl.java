package com.example.forum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.forum.entity.RecommendRecord;
import com.example.forum.entity.ViewRecord;
import com.example.forum.mapper.RecommendRecordMapper;
import com.example.forum.service.RecommendRecordService;
import com.example.forum.service.ViewRecordService;
import com.example.forum.util.UserCF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <pre>
 *     视频推荐业务逻辑实现类
 * </pre>
 */
@Service
public class RecommendRecordServiceImpl implements RecommendRecordService {


    @Autowired
    private RecommendRecordMapper recommendRecordMapper;

    @Autowired
    private ViewRecordService viewRecordService;

    @Override
    public BaseMapper<RecommendRecord> getRepository() {
        return recommendRecordMapper;
    }

    @Override
    public QueryWrapper<RecommendRecord> getQueryWrapper(RecommendRecord recommendRecord) {
        //对指定字段查询
        QueryWrapper<RecommendRecord> queryWrapper = new QueryWrapper<>();

        return queryWrapper;
    }

    @Override
    public RecommendRecord insertOrUpdate(RecommendRecord entity) {
        if (entity.getId() == null) {
            insert(entity);
        } else {
            update(entity);
        }
        return entity;
    }

    @Override
    public void delete(Long id) {
        recommendRecordMapper.deleteById(id);
    }

    @Override
    public List<RecommendRecord> findAll() {
        List<RecommendRecord> recommendRecordList = recommendRecordMapper.selectList(null);
        return recommendRecordList;
    }

    @Override
    public List<Long> generateRecommendList(Long userId) {
        List<ViewRecord> viewRecordList = viewRecordService.getRecentViewRecord();
        Map<Long, List<ViewRecord>> userViewListMap = viewRecordList.stream().collect(Collectors.groupingBy(p -> p.getUserId()));
        if (userViewListMap.get(userId) == null) {
            userViewListMap.put(userId, new ArrayList<>());
        }
        List<List<Long>> userViewList = new ArrayList<>();
        for (Map.Entry<Long, List<ViewRecord>> map : userViewListMap.entrySet()) {
            Long key = map.getKey();
            List<ViewRecord> value = map.getValue();
            // 将用户ID和该用户的访问视频ID存到list中
            List<Long> list = new ArrayList<>();
            list.add(key);
            for (ViewRecord viewRecord : value) {
                list.add(viewRecord.getPostId());
            }
            userViewList.add(list);
        }

        return UserCF.getRecommendList(userViewList, userId);
    }

    @Override
    public void deleteAndInsert(List<RecommendRecord> recommendRecords, Long userId) {
        // 删除旧的
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", userId);
        recommendRecordMapper.deleteByMap(map);

        // 添加新的
        this.batchInsert(recommendRecords);
    }
}
