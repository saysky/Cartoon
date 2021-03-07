package com.example.forum.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.forum.common.base.BaseEntity;
import lombok.Data;

/**
 * <pre>
 *     动漫栏目
 * </pre>
 *
 * @author : saysky
 * @date : 2017/11/30
 */
@Data
@TableName("category")
public class Category extends BaseEntity {

    /**
     * 栏目名称
     */
    private String cateName;

    /**
     * 栏目排序号
     */
    private Integer cateSort;

    /**
     * 栏目描述
     */
    private String cateDesc;
}
