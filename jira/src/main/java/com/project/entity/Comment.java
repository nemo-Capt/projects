package com.project.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Time;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private Time date;

    @ManyToOne
    @JoinColumn(name = "taskid", nullable = false)
    private Task task;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User user;

}
