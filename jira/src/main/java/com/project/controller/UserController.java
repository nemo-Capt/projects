package com.project.controller;

import com.project.entity.User;
import com.project.restservice.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserServiceImpl service;

    @Autowired
    public UserController(UserServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public List<User> findAll() {
        return service.getUsers();
    }

    @GetMapping(path = "/{id}")
    public Optional<User> find(@PathVariable("id") long id) {
        return service.getUser(id);
    }

    @PostMapping(consumes = "application/json")
    public void create(@RequestBody User user) {
        service.addUser(user);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") long id) {
        service.deleteUser(id);
    }

    @PutMapping(path = "/{id}")
    public void edit(@RequestBody User user, @PathVariable long id) {
        service.editUser(user, id);
    }
}

