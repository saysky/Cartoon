package com.example.forum.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.forum.common.base.BaseService;
import com.example.forum.entity.Category;

import java.util.List;

/**
 * <pre>
 *     栏目业务逻辑接口
 * </pre>
 *
 * @author : saysky
 * @date : 2017/11/30
 */
public interface CategoryService extends BaseService<Category, Long> {

    /**
     * 查询所有栏目目录,带count和根据level封装name
     *
     * @return 返回List集合
     */
    List<Category> findByUserId(Long userId);

    /**
     * 根据动漫Id获得栏目列表
     *
     * @param postId 动漫id
     * @return 栏目列表
     */
    Category findByPostId(Long postId);

    /**
     * 获得某个栏目的所有动漫数
     *
     * @param cateId 栏目Id
     * @return 动漫数
     */
    Integer countPostByCateId(Long cateId);

    /**
     * 根据用户ID删除
     *
     * @param userId
     * @return
     */
    Integer deleteByUserId(Long userId);

    /**
     * 将栏目ID列表转成栏目
     *
     * @param cateIds
     * @param userId
     * @return
     */
    List<Category> cateIdsToCateList(List<Long> cateIds, Long userId);
}
