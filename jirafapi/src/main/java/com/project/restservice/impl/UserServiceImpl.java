package com.project.restservice.impl;

import com.project.config.Constants;
import com.project.controller.ApiResponse;
import com.project.entity.PageResponse;
import com.project.entity.User;
import com.project.restservice.api.UserService;
import com.project.restservice.dto.RegistrationUserDTO;
import com.project.restservice.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final RestTemplate restTemplate;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(RestTemplateBuilder restTemplateBuilder, PasswordEncoder passwordEncoder) {
        this.restTemplate = restTemplateBuilder.build();
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public PageResponse<User> getAll(int page, int size) {
        PageResponse pageResponse =
                restTemplate.getForObject(
                        Constants.USER_URL + "?page=" + page + "&size=" + size,
                        PageResponse.class
                );
        return pageResponse;
    }

    @Override
    public User getUserByName(String username) {
        return restTemplate.getForObject(Constants.USER_URL + "/username/" + username, User.class);
    }

    @Override
    public User create(UserDTO userDTO) {
        String hashPassword = passwordEncoder.encode(userDTO.getPassword());
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(hashPassword);
        user.setEmail(userDTO.getEmail());
        user.setBanned(userDTO.isBanned());
        user.setRole(userDTO.getRole());

        restTemplate.postForEntity(Constants.USER_URL, user, ApiResponse.class).getBody();

        return user;
    }

    @Override
    public User register(RegistrationUserDTO registrationUserDTO) {
        String hashPassword = passwordEncoder.encode(registrationUserDTO.getPassword());
        User user = new User();
        user.setUsername(registrationUserDTO.getUsername());
        user.setEmail(registrationUserDTO.getEmail());
        user.setPassword(hashPassword);

        restTemplate.postForEntity(Constants.USER_URL + "/registration", user, ApiResponse.class).getBody();

        return user;
    }

    @Override
    public void setUserRole(Long id, String role) {
        restTemplate.put(
                Constants.USER_URL + "/setrole/" + id + "/role?role=" + role,
                null,
                ApiResponse.class);
    }

    @Override
    public void banUser(String username) {
        restTemplate.getForObject(Constants.USER_URL + "/ban/" + username, ApiResponse.class);
    }

    @Override
    public void unbanUser(String username) {
        restTemplate.getForObject(Constants.USER_URL + "/unban/" + username, ApiResponse.class);
    }
}
