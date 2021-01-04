package com.example.demoblog.dao;

import com.example.demoblog.entity.Blog;
import com.example.demoblog.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog,Integer> {
    public Page<Blog> findByUser(User user, Pageable pageable);
}
