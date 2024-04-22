package com.vision.blogowner.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vision.blogowner.entity.Blog;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

}
