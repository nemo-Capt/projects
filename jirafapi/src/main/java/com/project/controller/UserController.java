package com.project.controller;


import com.project.entity.User;
import com.project.restservice.api.UserService;
import com.project.restservice.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/username/{username}")
    public User getUserByName(@PathVariable(name = "username") String username) {

        User user = service.getUserByName(username);

        return user;
    }

    @PostMapping("")
    public ApiResponse create(@RequestBody UserDTO userDTO) {

        service.create(userDTO);

        return new ApiResponse("User successfully created");
    }

}
