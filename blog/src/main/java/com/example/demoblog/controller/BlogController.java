package com.example.demoblog.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demoblog.dao.BlogRepository;
import com.example.demoblog.dao.UserRepository;
import com.example.demoblog.entity.Blog;
import com.example.demoblog.entity.Image;
import com.example.demoblog.entity.User;
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
    private BlogRepository blogRepository;
    private UserRepository userRepository;

    @Autowired
    public BlogController(BlogRepository blogRepository,UserRepository userRepository) {
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("")
    public JSONObject postBlog(@RequestBody JSONObject object){
        String content = object.getString("content");
        String username = object.getString("username");
        object.getJSONArray("imgUrls").toArray();
        JSONArray imgUrls = object.getJSONArray("imgUrls");
        JSONObject mes = new JSONObject();
        if (content.length()>140){
            mes.put("state","Long content");
            return mes;
        }

        User user = userRepository.findByName(username);
        if(user==null){
            mes.put("state","User not exists");
            return mes;
        }
        //新加入微博
        Blog newBlog = new Blog();
        newBlog.setContent(content);
        newBlog.setUser(user);
        newBlog.setUname(user.getUserinfo().getUname());
        if(imgUrls!=null && !imgUrls.isEmpty()){
            List<Image> images = new ArrayList<>();
            for(int i=0;i<imgUrls.size();i++){
                String url = imgUrls.getString(i);
                Image image = new Image();
                image.setUrl(url);
                images.add(image);
            }
            newBlog.setImage(images);
        }
        newBlog.setTime(new Timestamp(System.currentTimeMillis()));
        blogRepository.save(newBlog);
        mes.put("state","OK");
        return mes ;
    }

    @GetMapping("")
    public JSONObject getALLBlog(@RequestParam(required = false,defaultValue = "0") int page){
        page = page<0?0:page;
        Sort sort = Sort.by(Sort.Direction.DESC,"time");
        Pageable pageable = PageRequest.of(page,2,sort);
        Page<Blog> blogs = blogRepository.findAll(pageable);
        for (Blog blog :blogs.getContent()){
            blog.setUname(blog.getUser().getName());
            blog.setUname(blog.getUser().getUserinfo().getUname());
        }
        JSONObject mes = new JSONObject();
        mes.put("blogs",blogs);
        return mes;
    }
    @GetMapping("/user")
    public JSONObject getBlogByName(@RequestParam(defaultValue = "")String username,
                                    @RequestParam(defaultValue = "0")int page,
                                    @RequestParam(defaultValue = "time")String type){
        page = page<0?0:page;
        Sort sort = Sort.by(Sort.Direction.DESC,type);
        Pageable pageable = PageRequest.of(page,2,sort);
        JSONObject mes = new JSONObject();
        if(username!=null&&!username.equals("")){
            User user = userRepository.findByName(username);
            Page<Blog> blogs = blogRepository.findByUser(user,pageable);
            mes.put("state","OK");
            mes.put("content",blogs);
        }
        else{
            mes.put("state","error");
        }
        return mes;
    }
}
