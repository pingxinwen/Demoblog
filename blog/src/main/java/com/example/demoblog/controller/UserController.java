package com.example.demoblog.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demoblog.dao.UserRepository;
import com.example.demoblog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
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
        //System.out.println("用户名："+username);
        //System.out.println("密码："+password);

        List<User> users = userRepository.findByName(username);
        if(users==null||users.size()==0) {
            return 0; //不存在的用户
        }
        for(User user:users){
            if (user.checkPassword(password)){
                return 1; //正确
            }
        }
        return -1; //错误密码
    }

    //提交注册信息
    @PostMapping("/register")
    public JSONObject register(@RequestBody final JSONObject object){
        String username = object.getString("name");
        String password = object.getString("password");
        String checkPass = object.getString("checkPassword");
        String email = object.getString("email");
        JSONObject mes = new JSONObject();
        if (username==null||password==null||checkPass==null||email==null){
            mes.put("mes","empty data");
        }
        else if (!password.equals(checkPass)){
            mes.put("mes","error check");
        }
        else if(userRepository.findByName(username).size()!=0){
            //用户已存在，考虑后续前端用axios时事检测这一项
            mes.put("mes","user exist");
        }
        else {
            User user = new User();
            user.setName(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setCreate_time(new Timestamp(System.currentTimeMillis()));
            userRepository.save(user);
            mes.put("mes","OK");
        }
        return mes;
    }

}
