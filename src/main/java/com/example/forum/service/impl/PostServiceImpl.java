package com.example.forum.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.forum.dto.PostQueryCondition;
import com.example.forum.dto.QueryCondition;
import com.example.forum.entity.*;
import com.example.forum.mapper.*;
import com.example.forum.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * <pre>
 *     动漫业务逻辑实现类
 * </pre>
 */
@Service
@Slf4j
public class PostServiceImpl implements PostService {


    @Autowired
    private PostMapper postMapper;

    @Autowired
    private PostCategoryRefMapper postCategoryRefMapper;

    @Autowired
    private PostTagRefMapper postTagRefMapper;

    @Autowired
    private TagMapper tagMapper;

    @Override
    @Async
    public void updatePostView(Long postId) {
        postMapper.incrPostViews(postId);
    }

    @Override
    @Async
    public void updateDanmuSize(Long postId) {
        postMapper.incrDanmuSize(postId);
    }


    @Override
    public Long getTotalPostViews() {
        return postMapper.getPostViewsSum();
    }

    @Override
    public void resetCommentSize(Long postId) {
        postMapper.resetCommentSize(postId);
    }


    @Override
    public void deleteByUserId(Long userId) {
        postMapper.deleteByUserId(userId);
    }

    @Override
    public Page<Post> findPostByCondition(PostQueryCondition condition, Page<Post> page) {
        List<Post> postList = postMapper.findPostByCondition(condition, page);
        for (Post post : postList) {
            List<Tag> tagList = tagMapper.findByPostId(post.getId());
            post.setTagList(tagList);
        }
        return page.setRecords(postList);
    }

    @Override
    public Page<Post> findRecommendPostByUserId(Long userId, Page<Post> page) {
        List<Post> postList = postMapper.findRecommendPostByUserId(userId, page);
        for (Post post : postList) {
            List<Tag> tagList = tagMapper.findByPostId(post.getId());
            post.setTagList(tagList);
        }
        return page.setRecords(postList);
    }

    @Override
    public BaseMapper<Post> getRepository() {
        return postMapper;
    }

    @Override
    public Post insert(Post post) {
        post.setPostViews(0L);
        post.setCommentSize(0L);
        post.setPostLikes(0L);
        postMapper.insert(post);
        //添加记录栏目关系
        if (post.getCategory() != null) {
            postCategoryRefMapper.insert(new PostCategoryRef(post.getId(), post.getCategory().getId()));
        }
        //添加记录标签关系
        if (post.getTagList() != null) {
            for (int i = 0; i < post.getTagList().size(); i++) {
                postTagRefMapper.insert(new PostTagRef(post.getId(), post.getTagList().get(i).getId()));
            }
        }
        return post;
    }

    @Override
    public Post update(Post post) {
        postMapper.updateById(post);
        if (post.getCategory() != null) {
            //添加栏目和记录关联
            postCategoryRefMapper.deleteByPostId(post.getId());
            //删除栏目和记录关联
            PostCategoryRef postCategoryRef = new PostCategoryRef(post.getId(), post.getCategory().getId());
            postCategoryRefMapper.insert(postCategoryRef);
        }
        if (post.getTagList() != null) {
            //删除标签和记录关联
            postTagRefMapper.deleteByPostId(post.getId());
            //添加标签和记录关联
            for (int i = 0; i < post.getTagList().size(); i++) {
                PostTagRef postTagRef = new PostTagRef(post.getId(), post.getTagList().get(i).getId());
                postTagRefMapper.insert(postTagRef);
            }
        }
        return post;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long postId) {
        Post post = this.get(postId);
        if (post != null) {
            postTagRefMapper.deleteByPostId(postId);
            postCategoryRefMapper.deleteByPostId(postId);
            postMapper.deleteById(post.getId());
        }
    }

    @Override
    public QueryWrapper<Post> getQueryWrapper(Post post) {
        //对指定字段查询
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        if (post != null) {
            if (StrUtil.isNotBlank(post.getPostTitle())) {
                queryWrapper.like("post_title", post.getPostTitle());
            }
            if (StrUtil.isNotBlank(post.getPostContent())) {
                queryWrapper.like("post_content", post.getPostContent());
            }
            if (post.getUserId() != null && post.getUserId() != -1) {
                queryWrapper.eq("user_id", post.getUserId());
            }
            if (post.getPostStatus() != null && post.getPostStatus() != -1) {
                queryWrapper.eq("post_status", post.getPostStatus());
            }
        }
        return queryWrapper;
    }

    @Override
    public Post insertOrUpdate(Post post) {
        if (post.getId() == null) {
            insert(post);
        } else {
            update(post);
        }
        return post;
    }

}

