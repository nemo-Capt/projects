package com.project.controller;

import com.project.entity.Role;
import com.project.restservice.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/roles")
public class RoleController {

    private final RoleService service;

    @Autowired
    public RoleController(RoleService service) {
        this.service = service;
    }

    @GetMapping
    public List<Role> findAll() {

        List<Role> roles = service.getRoles();
        return roles;
    }

    @GetMapping(path = "/{id}")
    public Optional<Role> find(@PathVariable("id") Long id) {

        Optional<Role> role = service.getRole(id);
        return role;
    }
}
