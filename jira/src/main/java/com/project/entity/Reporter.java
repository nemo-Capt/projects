package com.project.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Reporter {

    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "projectid", nullable = false)
    private Project project;
    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User user;
    @OneToMany(mappedBy = "reporter")
    private Set<Task> tasks;

    @JsonIgnore
    public Set<Task> getTasks() {
        return tasks;
    }

}
