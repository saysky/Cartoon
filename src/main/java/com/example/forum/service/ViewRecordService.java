package com.example.forum.service;


import com.example.forum.common.base.BaseService;
import com.example.forum.entity.Link;
import com.example.forum.entity.ViewRecord;

import java.util.List;

/**
 * 浏览记录服务
 *
 * @author : saysky
 */
public interface ViewRecordService extends BaseService<ViewRecord, Long> {

    /**
     * 添加浏览记录
     *
     * @param userId
     * @param postId
     */
    void insert(Long userId, Long postId);

    /**
     * 获得最新1000条
     *
     * @return
     */
    List<ViewRecord> getRecentViewRecord();

}
