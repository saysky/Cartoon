package com.example.forum.service;


import com.example.forum.common.base.BaseService;
import com.example.forum.entity.Comment;

import java.util.List;

/**
 * <pre>
 *     评论业务逻辑接口
 * </pre>
 *
 * @author : saysky
 * @date : 2018/1/22
 */
public interface CommentService extends BaseService<Comment, Long> {

    /**
     * 根据用户Id删除评论
     *
     * @param userId 用户Id
     */
    Integer deleteByUserId(Long userId);

    /**
     * 根据评论接受人Id删除评论
     *
     * @param acceptId 用户Id
     */
    Integer deleteByAcceptUserId(Long acceptId);

    /**
     * 根据动漫ID获得评论列表
     * @param postId
     * @return
     */
    List<Comment> findByPostId(Long postId);
}
