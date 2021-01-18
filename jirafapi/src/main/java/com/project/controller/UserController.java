package com.project.controller;


import com.project.entity.PageResponse;
import com.project.entity.User;
import com.project.restservice.api.UserService;
import com.project.restservice.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity getAll(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "3") int size) {

        PageResponse<User> users = service.getAll(page, size);

        return new ResponseEntity(users, HttpStatus.OK);
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

    @PutMapping("/{id}/role")
    public void changeRole(@PathVariable(name = "id") Long userId, @PathParam(value = "role") Long roleName) {

        service.setUserRole(userId, roleName);
    }

}
