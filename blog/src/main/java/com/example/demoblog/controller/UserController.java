package com.example.demoblog.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demoblog.dao.UserRepository;
import com.example.demoblog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //登录
    @PostMapping("/login")
    public int login(@RequestBody final JSONObject object){
        String username = object.getString("username");
        String password = object.getString("password");
        System.out.println("用户名："+username);
        System.out.println("密码："+password);

        List<User> users = userRepository.findByName(username);
        if(users.size()==0) {
            return 0; //不存在的用户
        }
        for(User user:users){
            if (user.checkPassword(password)){
                return 1; //正确
            }
        }
        return -1; //错误密码
    }

}
