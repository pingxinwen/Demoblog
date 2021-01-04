package com.example.demoblog.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demoblog.dao.BlogRepository;
import com.example.demoblog.dao.CommentRepository;
import com.example.demoblog.dao.UserRepository;
import com.example.demoblog.entity.Blog;
import com.example.demoblog.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
public class CommentController {
    private BlogRepository blogRepository;
    private UserRepository userRepository;
    private CommentRepository commentRepository;
    @Autowired
    public CommentController(BlogRepository blogRepository,UserRepository userRepository,
                             CommentRepository commentRepository) {
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @PostMapping("/comment")
    public JSONObject postComment(@RequestBody JSONObject body){
        JSONObject mes = new JSONObject();
        int id = Integer.parseInt(body.getString("blog_id"));
        Blog blog = blogRepository.findById(id);
        if(blog==null){
            mes.put("state","Not exist");
            return mes;
        }
        Comment comment = new Comment();
        comment.setContent(body.getString("content"));
        comment.setTime(new Timestamp(System.currentTimeMillis()));

        blog.getComments().add(comment);
        blog.setCommentNum(blog.getCommentNum()+1);
        blogRepository.save(blog);
        commentRepository.save(comment);
        mes.put("state","OK");
        mes.put("comment",comment);
        return  mes;
    }
}
