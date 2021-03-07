package com.example.forum.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.forum.common.base.BaseEntity;
import lombok.Data;

/**
 * 动漫栏目关联表
 *
 * @author 言曌
 * @date 2018/12/24 下午4:16
 */

@Data
@TableName("post_category_ref")
public class PostCategoryRef  extends BaseEntity {

    private static final long serialVersionUID = 1989776329130364722L;
    /**
     * 动漫Id
     */
    private Long postId;

    /**
     * 栏目Id
     */
    private Long cateId;

    public PostCategoryRef(Long postId, Long cateId) {
        this.postId = postId;
        this.cateId = cateId;
    }

    public PostCategoryRef() {
    }
}
