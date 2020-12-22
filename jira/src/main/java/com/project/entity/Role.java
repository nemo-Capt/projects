package com.project.entity;

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

    public Set<User> getUsers() {
        return users;
    }
}
