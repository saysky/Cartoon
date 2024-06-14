package com.example.forum.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.forum.common.base.BaseEntity;
import com.example.forum.util.RelativeDateFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author example
 */
@Data
@TableName("post")
@NoArgsConstructor
public class Post extends BaseEntity {

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 动漫标题
     */
    private String postType;

    /**
     * 动漫标题
     */
    private String postTitle;

    /**
     * 动漫内容 html格式
     */
    private String postContent;

    /**
     * 动漫摘要
     */
    private String postSummary;

    /**
     * 缩略图
     */
    private String postThumbnail;

    /**
     * 视频地址
     */
    private String videoUrl;

    /**
     * 0 已发布
     * 1 草稿
     * 2 回收站
     */
    private Integer postStatus;

    /**
     * 动漫访问量
     */
    private Long postViews;

    /**
     * 点赞访问量
     */
    private Long postLikes;

    /**
     * 弹幕数
     */
    private Long danmuSize;

    /**
     * 评论数量(冗余字段，加快查询速度)
     */
    private Long commentSize;


    /**
     * 发表用户 多对一
     */
    @TableField(exist = false)
    private User user;

    /**
     * 动漫所属栏目
     */
    @TableField(exist = false)
    private Category category;

    /**
     * 动漫所属标签
     */
    @TableField(exist = false)
    private List<Tag> tagList = new ArrayList<>();

    /**
     * 动漫的评论
     */
    @TableField(exist = false)
    private List<Comment> comments = new ArrayList<>();

    /**
     * 更新时间
     */
    @TableField(exist = false)
    private String createTimeStr;

    public String getCreateTimeStr() {
        return RelativeDateFormat.format(getCreateTime());
    }

    public Post(Long userId, String postTitle, String postThumbnail, Integer postStatus) {
        this.userId = userId;
        this.postTitle = postTitle;
        this.postThumbnail = postThumbnail;
        this.postStatus = postStatus;
        this.postContent = "";
        this.postLikes = 0L;
        this.postViews = 0L;
        this.commentSize = 0L;
        this.postType = "post";
        this.postStatus = 0;
        this.postSummary = "";
    }
