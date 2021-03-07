package com.example.forum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.forum.entity.ViewRecord;
import com.example.forum.mapper.ViewRecordMapper;
import com.example.forum.service.ViewRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <pre>
 *     查看记录业务逻辑实现类
 * </pre>
 */
@Service
public class ViewRecordServiceImpl implements ViewRecordService {


    @Autowired
    private ViewRecordMapper viewRecordMapper;

    @Override
    public BaseMapper<ViewRecord> getRepository() {
        return viewRecordMapper;
    }

    @Override
    public QueryWrapper<ViewRecord> getQueryWrapper(ViewRecord viewRecord) {
        //对指定字段查询
        QueryWrapper<ViewRecord> queryWrapper = new QueryWrapper<>();

        return queryWrapper;
    }

    @Override
    public ViewRecord insertOrUpdate(ViewRecord entity) {
        if (entity.getId() == null) {
            insert(entity);
        } else {
            update(entity);
        }
        return entity;
    }

    @Override
    public void delete(Long id) {
        viewRecordMapper.deleteById(id);
    }

    @Override
    public List<ViewRecord> findAll() {
        List<ViewRecord> viewRecordList = viewRecordMapper.selectList(null);
        return viewRecordList;
    }

    @Override
    public void insert(Long userId, Long postId) {
        ViewRecord viewRecord = new ViewRecord();
        viewRecord.setUserId(userId);
        viewRecord.setPostId(postId);
        viewRecordMapper.insert(viewRecord);
    }

    @Override
    public List<ViewRecord> getRecentViewRecord() {
        return viewRecordMapper.getRecentViewRecord();
    }
}
