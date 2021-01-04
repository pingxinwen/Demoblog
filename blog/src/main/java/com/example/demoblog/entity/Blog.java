package com.example.demoblog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JsonIgnore
    private User user;

    private String content;

    @Transient
    private String uname;
    @Transient
    private String username;
    private Timestamp time;

    @OneToMany
    private List<Image> image;

    @OneToMany
    private List<Comment> comments;
    private int comment_num;

    @OneToMany
    @JsonIgnore
    private List<Thumb> thumbs;
    private int likes;
    @Transient
    private boolean is_like;

    public Blog(){
        this.comment_num=0;
        this.likes=0;
    }
}
