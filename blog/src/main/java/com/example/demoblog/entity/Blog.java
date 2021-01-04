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

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private User user;

    private String content;

    @Transient
    private String uname;
    @Transient
    private String username;
    private Timestamp time;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Image> image;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments;

    private int commentNum;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Thumb> thumbs;
    private int likes;
    @Transient
    private boolean like;

    public Blog(){
        this.commentNum=0;
        this.likes=0;
    }
}
