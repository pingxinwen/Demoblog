package com.example.demoblog.dao;

import com.example.demoblog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    List<User>findById(int id);
    List<User>findByName(String name);
}
