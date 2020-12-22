package com.project.view;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role {

    @Id
    private long id;
    private String rolename;
    @OneToMany(mappedBy="role")
    private Set<User> users;

}
