package com.example.forum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.forum.dto.PostQueryCondition;
import com.example.forum.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author liuyanzhao
 */
@Mapper
public interface PostMapper extends BaseMapper<Post> {

    /**
     * 获取所有动漫阅读量总和
     *
     * @return Long
     */
    Long getPostViewsSum();

    /**
     * 重置评论数量
     *
     * @return 数量
     */
    Integer resetCommentSize(Long postId);

    /**
     * 根据用户Id删除
     *
     * @return 影响行数
     */
    Integer deleteByUserId(Long userId);

    /**
     * 动漫点赞量+1
     *
     * @param postId
     * @return
     */
    Integer incrPostLikes(Long postId);

    /**
     * 动漫访问量+1
     *
     * @param postId
     * @return
     */
    Integer incrPostViews(Long postId);

    /**
     * 弹幕数+1
     *
     * @param postId
     * @return
     */
    Integer incrDanmuSize(Long postId);


    /**
     * 获得今日新增数量
     *
     * @return
     */
    Integer getTodayCount();

    /**
     * 跟根据条件查询
     *
     * @param condition
     * @param page
     * @return
     */
    List<Post> findPostByCondition(@Param("condition") PostQueryCondition condition, Page page);

    /**
     * 根据用户ID查询其推荐的视频
     *
     * @param userId
     * @param page
     * @return
     */
    List<Post> findRecommendPostByUserId(@Param("userId") Long userId, Page page);


}

