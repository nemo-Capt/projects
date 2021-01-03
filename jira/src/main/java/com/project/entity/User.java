package com.project.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setId(long id) {
        this.id = id;
    }
}
