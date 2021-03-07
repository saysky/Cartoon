package com.example.forum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.forum.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author liuyanzhao
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 获得某篇动漫的栏目列表
     *
     * @param postId 动漫Id
     * @return List
     */
    Category findByPostId(Long postId);


    /**
     * 获得子栏目Id列表
     *
     * @param  pathTrace /138/ 这种格式
     * @return 子栏目Id列表
     */
    List<Long> selectChildCateIds(@Param("pathTrace") String pathTrace);

    /**
     * 根据用户ID删除
     * @param userId
     * @return
     */
    Integer deleteByUserId(Long userId);
}

