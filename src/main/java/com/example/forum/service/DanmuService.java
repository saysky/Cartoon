package com.example.forum.service;

import com.example.forum.common.base.BaseService;
import com.example.forum.entity.Danmu;

import java.util.List;

/**
 * @author 言曌
 * @date 2021/3/7 2:25 下午
 */

public interface DanmuService extends BaseService<Danmu, Long> {

    /**
     * 根据动漫ID查询弹幕列表
     *
     * @param postId
     * @return
     */
    List<Danmu> findByPostId(Long postId);
}
