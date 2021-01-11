package com.project.controller;

import com.project.restservice.api.UserService;
import com.project.restservice.dto.RegistrationUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService service;

    @Autowired
    public AuthController(UserService service) {
        this.service = service;
    }

    @PostMapping("/signup")
    public ApiResponse registration(@RequestBody RegistrationUserDTO registrationUserDTO) {

        service.register(registrationUserDTO);

        return new ApiResponse("User successfully registered");
    }

}
