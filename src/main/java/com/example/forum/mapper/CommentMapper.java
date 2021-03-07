package com.example.forum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.forum.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author liuyanzhao
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 查询前limit条评论
     *
     * @param limit 查询数量
     * @return 评论列表
     */
    List<Comment> findLatestCommentByLimit(Integer limit);

    /**
     * 根据用户Id删除
     *
     * @param userId 用户Id
     * @return 影响行数
     */
    Integer deleteByUserId(Long userId);

    /**
     * 根据用户Id删除
     *
     * @param userId 用户Id
     * @return 影响行数
     */
    Integer deleteByAcceptUserId(Long userId);

    /**
     * 获得某个ip用户最新的评论
     *
     * @param ip IP地址
     * @return 评论
     */
    Comment getLatestCommentByIP(String ip);

    /**
     * 获得子评论Id列表
     *
     * @param pathTrace 评论pathTrace封装
     * @return 评论Id列表
     */
    List<Long> selectChildCommentIds(@Param("pathTrace") String pathTrace);

    /**
     * 获得某个用户最新收到的评论
     *
     * @param userId 收到评论的用户
     * @param limit  查询数量
     * @return
     */
    List<Comment> getLatestCommentByAcceptUser(@Param("userId") Long userId,
                                               @Param("limit") Integer limit);

    /**
     * 根据动漫ID获得评论列表
     * @param postId
     * @return
     */
    List<Comment> findByPostId(Long postId);
}

