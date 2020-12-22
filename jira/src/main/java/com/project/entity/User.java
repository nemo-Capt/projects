package com.project.entity;

import javax.persistence.*;

@Entity
public class User {

    @Id
    private long id;
    private String username;
    private String email;
    private String password;
    private boolean banned;
    @ManyToOne
    @JoinColumn(name = "roleid", nullable = false)
    private Role role;

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isBanned() {
        return banned;
    }

    public Role getRole() {
        return role;
    }
}
