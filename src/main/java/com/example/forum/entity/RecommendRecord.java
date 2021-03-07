package com.example.forum.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.forum.common.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 推荐视频列表
 *
 * @author liuyanzhao
 */
@Data
@TableName("recommend_record")
@NoArgsConstructor
public class RecommendRecord extends BaseEntity {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 视频ID
     */
    private Long postId;

    public RecommendRecord(Long userId, Long postId) {
        this.userId = userId;
        this.postId = postId;
    }
}
