package com.example.forum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.forum.entity.PostCategoryRef;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author liuyanzhao
 */
@Mapper
public interface PostCategoryRefMapper extends BaseMapper<PostCategoryRef> {

    /**
     * 根据动漫Id删除记录
     *
     * @param postId 动漫Id
     * @return 影响行数
     */
    Integer deleteByPostId(Long postId);

    /**
     * 根据栏目Id删除记录
     *
     * @param cateId 栏目Id
     * @return 影响行数
     */
    Integer deleteByCateId(Long cateId);

    /**
     * 根据栏目Id查询动漫Id
     *
     * @param cateId 栏目Id
     * @return 动漫Id列表
     */
    List<Long> selectPostIdByCateId(Long cateId);

    /**
     * 根据动漫Id查询栏目Id
     *
     * @param postId 动漫Id
     * @return 栏目Id列表
     */
    List<Long> selectCateIdByPostId(Long postId);

    /**
     * 统计某篇栏目的动漫数
     *
     * @param cateId 栏目Id
     * @return 动漫Id列表
     */
    Integer countPostByCateId(Long cateId);
}

