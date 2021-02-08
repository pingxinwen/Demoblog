package com.example.demoblog.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demoblog.entity.Blog;

public interface BlogService {
    JSONObject addBlog(JSONObject object);
    JSONObject getBlog(int page,String type,String username);
    JSONObject getBlogByName(int page,String type,String name);
}
