package com.example.demoblog.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demoblog.dao.UserInfoRepository;
import com.example.demoblog.dao.UserRepository;
import com.example.demoblog.entity.User;
import com.example.demoblog.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class UserController {
    private UserRepository userRepository;
    private UserInfoRepository userInfoRepository;

    @Autowired
    public UserController(UserRepository userRepository, UserInfoRepository userInfoRepository) {
        this.userRepository = userRepository;
        this.userInfoRepository = userInfoRepository;
    }

    //登录
    @PostMapping("/login")
    public JSONObject login(@RequestBody final JSONObject object) {
        String username = object.getString("username");
        String password = object.getString("password");
        JSONObject mes = new JSONObject();


        User user = userRepository.findByName(username);
        if (user == null) {
            mes.put("state", "Not exist"); //不存在的用户
        } else {
            if (user.checkPassword(password)) {
                mes.put("state", "OK");//正确
                UserInfo info = user.getUserinfo();
                mes.put("info", info);
                mes.put("username",username);
                System.out.println(mes.toString());
            }
            else
                mes.put("state", "Wrong password"); //错误密码
        }
        return mes;
    }

    //提交注册信息
    @PostMapping("/register")
    public JSONObject register(@RequestBody final JSONObject object) {
        String username = object.getString("name");
        String password = object.getString("password");
        String checkPass = object.getString("checkPassword");
        String email = object.getString("email");

        JSONObject mes = new JSONObject();
        if (username == null || password == null || checkPass == null || email == null) {
            mes.put("mes", "empty data");
        } else if (!password.equals(checkPass)) {
            mes.put("mes", "error check");
        } else if (userRepository.findByName(username) != null) {
            //用户已存在，考虑后续前端用axios时事检测这一项
            mes.put("mes", "user exist");
        } else {
            User user = new User();
            UserInfo userinfo = new UserInfo();
            userinfo.setUsername(username);
            userinfo.setUser(user);
            userInfoRepository.save(userinfo);
            user.setName(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setCreate_time(new Timestamp(System.currentTimeMillis()));
            user.setUserinfo(userinfo);
            userRepository.save(user);
            mes.put("mes", "OK");
        }
        return mes;
    }

}
