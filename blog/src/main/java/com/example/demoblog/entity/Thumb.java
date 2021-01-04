package com.example.demoblog.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Thumb {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Blog blog;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
}
