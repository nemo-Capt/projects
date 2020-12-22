package com.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role {

    @Id
    private long id;
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
