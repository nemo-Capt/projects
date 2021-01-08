package com.project.restservice.dto;

import com.project.entity.Assignee;
import com.project.entity.Task;

import java.util.List;
import java.util.Set;

public class ProjectDTO {

    private Long id;
    private String name;
    private String stage;
    private String duedate;
    private String estimatedtime;
    private String desc;
    private List<Task> tasks;
    private List<Assignee> assignees;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    public String getEstimatedtime() {
        return estimatedtime;
    }

    public void setEstimatedtime(String estimatedtime) {
        this.estimatedtime = estimatedtime;
    }

    public List<Assignee> getAssignees() {
        return assignees;
    }

    public void setAssignees(List<Assignee> assignees) {
        this.assignees = assignees;
    }
}
