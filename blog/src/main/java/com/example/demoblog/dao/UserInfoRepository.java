package com.example.demoblog.dao;

import com.example.demoblog.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo,Integer> {
    UserInfo findByUsername(String username);
}
