package com.demo.bloginfo.service;


import com.demo.bloginfo.entity.BlogInfo;
import com.demo.bloginfo.repository.BlogInfoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogInfoService {

    @Autowired
    private BlogInfoRepository blogInfoRepository;

    public List<BlogInfo> getAllBlogs() {
        return blogInfoRepository.findAll();
    }

    public Optional<BlogInfo> getBlogById(Long id) {
        return blogInfoRepository.findById(id);
    }

    public BlogInfo createBlog(BlogInfo blogInfo) {
        return blogInfoRepository.save(blogInfo);
    }

    public void deleteBlog(Long id) {
        blogInfoRepository.deleteById(id);
    }
}
