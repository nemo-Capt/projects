package com.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.sql.Time;
import java.util.Set;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "projectid", nullable = false)
    private Project project;
    @ManyToOne
    @JoinColumn(name = "assigneeid", nullable = false)
    private Assignee assignee;
    private String desc;
    private String priority;
    private Time duedate;
    private Time estimatedtime;
    @ManyToOne
    @JoinColumn(name = "statusid", nullable = false)
    private Status status;

    @OneToMany(mappedBy = "task")
    private Set<Comment> comments;

    @JsonIgnore
    public Set<Comment> getComments() {
        return comments;
    }

}
