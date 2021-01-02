package com.example.demoblog.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserInfo {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String birth;
    private String sex;
    private String signature;
    private String special_name;

    public void setId(int id){
        this.id=id;
    }

    public void setBirth(String birth){
        this.birth =birth;
    }

    public void setSex(String sex){
        this.sex=sex;
    }

    public void setSignature(String signature){
        this.signature=signature;
    }

    public String getBirth(){
        return birth;
    }

    public String getSex(){
        return sex;
    }

    public String getSignature(){
        return signature;
    }

    public int getId() {
        return id;
    }

    public String getSpecial_name() {
        return special_name;
    }

    public void setSpecial_name(String special_name) {
        this.special_name = special_name;
    }
}
