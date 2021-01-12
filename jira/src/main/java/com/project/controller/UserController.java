package com.project.controller;

import com.project.entity.User;
import com.project.restservice.api.UserService;
import com.project.restservice.dto.UserDTO;
import com.project.restservice.dto.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public Page<UserDTO> findAll(Pageable pageable) {

        Page<UserDTO> userDTOs = service.getUsers(pageable);
        return userDTOs;
    }

    @GetMapping(path = "/{id}")
    public UserDTO find(@PathVariable("id") long id) {
        User user = service.getUser(id);
        UserDTO userDTO = UserMapper.createDTO(user);

        return userDTO;
    }

    @GetMapping("/username/{username}")
    public UserDTO getUserByName(@PathVariable(name = "username") String username) {

        User user = service.getUserByName(username);
        UserDTO userDTO = UserMapper.createDTO(user);

        return userDTO;
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

    @PutMapping(path = "/setrole/{id}/role")
    public void setUserRole(@PathVariable long id, @RequestParam long roleId) {
        service.setUserRole(id, roleId);
    }
}

