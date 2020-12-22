package com.project.view;

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
    @JoinColumn(name="cart_id", nullable=false)
    private Role role;
}
