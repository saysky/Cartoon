package com.example.forum.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.forum.entity.Danmu;
import com.example.forum.entity.Link;
import com.example.forum.mapper.DanmuMapper;
import com.example.forum.service.DanmuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 言曌
 * @date 2021/3/7 2:25 下午
 */
@Service
public class DanmuServiceImpl implements DanmuService {

    @Autowired
    private DanmuMapper danmuMapper;

    @Override
    public BaseMapper<Danmu> getRepository() {
        return danmuMapper;
    }

    @Override
    public QueryWrapper<Danmu> getQueryWrapper(Danmu danmu) {
        //对指定字段查询
        QueryWrapper<Danmu> queryWrapper = new QueryWrapper<>();
        if (danmu != null) {
            if (danmu.getUserId() != null && danmu.getUserId() != -1) {
                queryWrapper.like("user_id", danmu.getUserId());
            }
            if (danmu.getPostId() != null && danmu.getPostId() != -1) {
                queryWrapper.like("post_id", danmu.getPostId());
            }
        }
        return queryWrapper;
    }

    @Override
    public List<Danmu> findByPostId(Long postId) {
        QueryWrapper<Danmu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("post_id", postId);
        queryWrapper.orderByAsc("create_time");
        return danmuMapper.selectList(queryWrapper);
    }
}
