package com.example.forum.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.forum.common.base.BaseEntity;
import com.example.forum.util.RelativeDateFormat;
import lombok.Data;

import java.util.List;

/**
 * 评论
 */
@Data
@TableName("comment")
public class Comment extends BaseEntity {

    /**
     * 动漫ID
     */
    private Long postId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 上一级
     */
    private Long commentParent = 0L;

    /**
     * 关系路径
     */
    private String pathTrace;

    /**
     * 接受者用户Id
     */
    private Long acceptUserId;

    /**
     * 点赞数
     */
    private Long likeCount;

    /**
     * 点踩数
     */
    private Long dislikeCount;

    /**
     * 评论动漫
     */
    @TableField(exist = false)
    private Post post;


    /**
     * 评论人
     */
    @TableField(exist = false)
    private User user;


    /**
     * 当前评论下的所有子评论
     */
    @TableField(exist = false)
    private List<Comment> childComments;

    /**
     * 创建时间
     */
    @TableField(exist = false)
    private String createTimeStr;


    public String getCreateTimeStr() {
        return RelativeDateFormat.format(getCreateTime());
    }
}
