package com.example.forum.service;


import com.example.forum.common.base.BaseService;
import com.example.forum.entity.RecommendRecord;

import java.util.List;

/**
 * 视频推荐服务
 *
 * @author : saysky
 */
public interface RecommendRecordService extends BaseService<RecommendRecord, Long> {

    /**
     * 生成推荐列表
     *
     * @param userId
     * @return
     */
    List<Long> generateRecommendList(Long userId);

    /**
     * 删除旧的添加新的
     * @param recommendRecords
     * @param userId
     */
    void deleteAndInsert(List<RecommendRecord> recommendRecords, Long userId);
}
