package com.example.demoblog.dao;

import com.example.demoblog.entity.Blog;
import com.example.demoblog.entity.Thumb;
import com.example.demoblog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThumbRepository extends JpaRepository<Thumb,Integer> {
    List<Thumb> findThumbByBlogAndUser(Blog blog, User user);
}
