package com.example.forum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.forum.entity.ViewRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author liuyanzhao
 */
@Mapper
public interface ViewRecordMapper extends BaseMapper<ViewRecord> {

    /**
     * 获得最新的一些浏览记录
     *
     * @return
     */
    List<ViewRecord> getRecentViewRecord();

}

