package com.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.sql.Time;
import java.util.Set;


@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String stage;
    private Time duedate;
    private Time estimatedtime;
    private String desc;
    @OneToMany(mappedBy = "project")
    private Set<Task> tasks;

    @JsonIgnore
    public Set<Task> getTasks() {
        return tasks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public Time getDuedate() {
        return duedate;
    }

    public void setDuedate(Time duedate) {
        this.duedate = duedate;
    }

    public Time getEstimatedtime() {
        return estimatedtime;
    }

    public void setEstimatedtime(Time estimatedtime) {
        this.estimatedtime = estimatedtime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}
