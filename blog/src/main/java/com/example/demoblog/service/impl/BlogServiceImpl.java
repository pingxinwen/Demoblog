package com.example.demoblog.service.impl;

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
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    private BlogRepository blogRepository;
    private UserRepository userRepository;

    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository,UserRepository userRepository) {
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
    }

    @Override
    public JSONObject addBlog(JSONObject object) {
        String content = object.getString("content");
        String username = object.getString("username");//发送用户名
        object.getJSONArray("imgUrls").toArray();
        JSONArray imgUrls = object.getJSONArray("imgUrls");

        JSONObject mes = new JSONObject();
        if (content.length()>140){
            mes.put("mes","Long content");
            return mes;//超出长度限制
        }

        User user = userRepository.findByName(username);
        if(user==null){
            mes.put("mes","User not exists");
            return mes;
            //检验用户是否存在，验证登录则不需要
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
        mes.put("mes","OK");
        mes.put("blog",newBlog);
        return mes;
    }

    @Override
    public JSONObject getBlog(int page, String type, String username) {
        page = Math.max(page, 0);
        Sort sort = Sort.by(Sort.Direction.DESC,type);
        Pageable pageable = PageRequest.of(page,2,sort);
        Page<Blog> blogs = blogRepository.findAll(pageable);
        User requestUser = userRepository.findByName(username);
        for (Blog blog :blogs.getContent()){
            User user = blog.getUser();
            //System.out.println(user.getName()+"\t"+user.getUserinfo().getUname());
            blog.setUsername(user.getName());
            blog.setUname(user.getUserinfo().getUname());
            if(requestUser!=null){
                for(Thumb thumb:blog.getThumbs()){
                    if (thumb.getUser().equals(user)){
                        blog.setLike(true);
                        break;
                    }
                }
            }
        }
        JSONObject mes = new JSONObject();
        mes.put("blogs",blogs);
        return mes;
    }

    @Override
    public JSONObject getBlogByName(int page, String type, String name) {
        page = Math.max(page, 0);
        System.out.println(type);
        Sort sort = Sort.by(Sort.Direction.DESC,type);
        Pageable pageable = PageRequest.of(page,2,sort);
        JSONObject mes = new JSONObject();
        if(name!=null&&!name.equals("")){
            User user = userRepository.findByName(name);
            Page<Blog> blogs = blogRepository.findByUser(user,pageable);
            for(Blog blog :blogs.getContent()){
                User user1 = blog.getUser();
                //System.out.println(user1.getName()+"\t"+user1.getUserinfo().getUname());
                blog.setUsername(user1.getName());
                blog.setUname(user1.getUserinfo().getUname());
                for(Thumb thumb:blog.getThumbs()){
                    if (thumb.getUser().equals(user)){
                        blog.setLike(true);
                        break;
                    }
                }
            }
            mes.put("state","OK");
            mes.put("content",blogs);
        }
        else{
            mes.put("state","error");
        }
        return mes;
    }
}
