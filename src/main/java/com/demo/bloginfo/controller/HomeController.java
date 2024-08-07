package com.demo.bloginfo.controller;

import com.demo.bloginfo.entity.BlogInfo;
import com.demo.bloginfo.service.BlogInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/blogs")
public class HomeController {

    @Autowired
    private BlogInfoService blogInfoService;

    @GetMapping
    public ResponseEntity<List<BlogInfo>> getAllBlogs() {
        List<BlogInfo> blogs = blogInfoService.getAllBlogs();
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogInfo> getBlogById(@PathVariable Long id) {
        Optional<BlogInfo> blog = blogInfoService.getBlogById(id);
        return blog.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<BlogInfo> createBlog(@RequestBody BlogInfo blogInfo) {
        BlogInfo createdBlog = blogInfoService.createBlog(blogInfo);
        return new ResponseEntity<>(createdBlog, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long id) {
        blogInfoService.deleteBlog(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
