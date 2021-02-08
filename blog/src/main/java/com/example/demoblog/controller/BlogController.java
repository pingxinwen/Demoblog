package com.example.demoblog.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demoblog.dao.BlogRepository;
import com.example.demoblog.dao.UserRepository;
import com.example.demoblog.entity.Blog;
import com.example.demoblog.entity.Image;
import com.example.demoblog.entity.Thumb;
import com.example.demoblog.entity.User;
import com.example.demoblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {
    private BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService){
        this.blogService = blogService;
    }

    @PostMapping("")
    public JSONObject postBlog(@RequestBody JSONObject object){
        JSONObject mes = this.blogService.addBlog(object);

        return mes ;
    }

    @GetMapping("")
    public JSONObject getALLBlog(@RequestParam(required = false,defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "time") String type,
                                 @RequestParam(required = false,defaultValue = "")String username){
        JSONObject mes = this.blogService.getBlog(page, type, username);
        return mes;
    }

    @GetMapping("/user")
    public JSONObject getBlogByName(@RequestParam(defaultValue = "")String username,
                                    @RequestParam(defaultValue = "0")int page,
                                    @RequestParam(defaultValue = "time")String type){
        JSONObject mes = this.blogService.getBlogByName(page, type, username);
        return mes;
    }
}
