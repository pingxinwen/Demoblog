package com.example.demoblog.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demoblog.dao.BlogRepository;
import com.example.demoblog.dao.ThumbRepository;
import com.example.demoblog.dao.UserRepository;
import com.example.demoblog.entity.Blog;
import com.example.demoblog.entity.Thumb;
import com.example.demoblog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThumbController {
    private ThumbRepository thumbRepository;
    private BlogRepository blogRepository;
    private UserRepository userRepository;

    @Autowired
    public ThumbController(ThumbRepository thumbRepository, BlogRepository blogRepository, UserRepository userRepository) {
        this.thumbRepository = thumbRepository;
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
    }


    @PostMapping("/thumb")
    public JSONObject setThumb(@RequestBody JSONObject object){
        JSONObject mes = new JSONObject();
        String blogId = object.getString("blogId");
        int id = Integer.parseInt(blogId);
        Blog blog = blogRepository.findById(id);
        if (blog==null){
            mes.put("state","Not exist");
            return mes;
        }
        User user = userRepository.findByName(object.getString("username"));
        if (user==null){
            mes.put("state","Not exist");
            return mes;
        }
        if(thumbRepository.findThumbByBlogAndUser(blog,user).size()!=0){
            mes.put("state","already exist");
        }
        Thumb thumb = new Thumb();
        thumb.setBlog(blog);
        thumb.setUser(user);
        blog.getThumbs().add(thumb);
        blog.setLikes(blog.getThumbs().size());
        blogRepository.save(blog);
        thumbRepository.save(thumb);

        mes.put("state","OK");
        return mes;
    }
}
