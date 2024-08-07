package com.demo.bloginfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.bloginfo.entity.BlogInfo;

public interface BlogInfoRepository extends JpaRepository<BlogInfo, Long> {

}