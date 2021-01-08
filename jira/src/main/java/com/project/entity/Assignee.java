package com.project.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Entity
public class Assignee {

    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "projectid", nullable = false)
    private Project project;
    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User user;
    @OneToMany(mappedBy = "assignee")
    private List<Task> tasks;

    @JsonIgnore
    public List<Task> getTasks() {
        return tasks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
