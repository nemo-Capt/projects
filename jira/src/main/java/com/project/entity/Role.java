package com.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rolename;
    @OneToMany(mappedBy = "role")
    private Set<User> users;

    public long getId() {
        return id;
    }

    public String getRolename() {
        return rolename;
    }

    @JsonIgnore
    public Set<User> getUsers() {
        return users;
    }
}
