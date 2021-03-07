package com.example.forum.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.forum.common.base.BaseService;
import com.example.forum.dto.PostQueryCondition;
import com.example.forum.entity.Post;

/**
 * <pre>
 *     记录/页面业务逻辑接口
 * </pre>
 */
public interface PostService extends BaseService<Post, Long> {

    /**
     * 修改记录阅读量
     *
     * @param postId 记录Id
     * @return 记录访问量
     */
    void updatePostView(Long postId);

    /**
     * 增加弹幕数量
     * @param postId
     */
    void updateDanmuSize(Long postId);

    /**
     * 获取所有记录的阅读量
     *
     * @return Long
     */
    Long getTotalPostViews();

    /**
     * 更新记录评论数
     *
     * @param postId 记录Id
     */
    void resetCommentSize(Long postId);

    /**
     * 删除用户的记录
     *
     * @param userId 用户Id
     */
    void deleteByUserId(Long userId);

    /**
     * 根据条件获得列表
     *
     * @param condition
     * @return
     */
    Page<Post> findPostByCondition(PostQueryCondition condition, Page<Post> page);

    /**
     * 根据用户推荐
     *
     * @param userId
     * @return
     */
    Page<Post> findRecommendPostByUserId(Long userId, Page<Post> page);


}
