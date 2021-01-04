package com.example.demoblog.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Thumb {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Blog blog;

    @OneToOne
    private User user;
}
