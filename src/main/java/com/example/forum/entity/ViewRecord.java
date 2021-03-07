package com.example.forum.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.forum.common.base.BaseEntity;
import lombok.Data;

/**
 * 视频浏览记录
 *
 * @author liuyanzhao
 */
@Data
@TableName("view_record")
public class ViewRecord extends BaseEntity {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 视频ID
     */
    private Long postId;
}
