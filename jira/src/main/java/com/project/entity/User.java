package com.project.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private boolean banned;
    @ManyToOne
    @JoinColumn(name = "roleid", nullable = false)
    private Role role;
    @OneToMany(mappedBy = "user")
    private Set<Task> tasks;
    @OneToMany(mappedBy = "user")
    private Set<Comment> comments;

    @JsonIgnore
    public Set<Task> getTasks() {
        return tasks;
    }

    @JsonIgnore
    public Set<Comment> getComments() {
        return comments;
    }

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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }
}
