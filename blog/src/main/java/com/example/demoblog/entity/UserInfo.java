package com.example.demoblog.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(mappedBy = "userinfo", cascade = CascadeType.ALL)
    @JSONField(serialize = false)
    @JsonIgnore
    private User user;

    private String username;
    private String uname;  //昵称
    private String sex;    //性别
    private String introduction;

    public UserInfo() {
        uname = "";
        sex = "Unknown";
        introduction = "";
    }
}
