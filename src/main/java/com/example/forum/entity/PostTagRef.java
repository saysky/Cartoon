package com.example.forum.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.forum.common.base.BaseEntity;
import lombok.Data;

/**
 * 动漫标签关联表
 *
 * @author 言曌
 * @date 2018/12/24 下午4:16
 */

@Data
@TableName("post_tag_ref")
public class PostTagRef  extends BaseEntity {

    private static final long serialVersionUID = 1989776329130364722L;
    /**
     * 动漫Id
     */
    private Long postId;

    /**
     * 标签Id
     */
    private Long tagId;

    public PostTagRef(Long postId, Long tagId) {
        this.postId = postId;
        this.tagId = tagId;
    }

    public PostTagRef() {
    }
}
