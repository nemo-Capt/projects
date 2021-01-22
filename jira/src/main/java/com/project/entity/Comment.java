package com.project.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private String date;

    @ManyToOne
    @JoinColumn(name = "taskid", nullable = false)
    private Task task;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTask() {
        return task.getName();
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getUser() {
        return user.getUsername();
    }

    public void setUser(User user) {
        this.user = user;
    }
}
