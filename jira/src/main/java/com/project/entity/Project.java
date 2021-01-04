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


}
