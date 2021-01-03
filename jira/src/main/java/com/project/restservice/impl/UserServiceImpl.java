package com.project.restservice.impl;


import com.project.entity.User;
import com.project.repository.UserRepository;
import com.project.restservice.api.UserService;
import com.project.restservice.dto.UserDTO;
import com.project.restservice.dto.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    public Page<UserDTO> getUsers(Pageable pageable) {
        return repository.findAll(pageable)
                .map(UserMapper::createDTO);
    }

    public void addUser(User user) {
        repository.save(user);
    }

    public User getUser(Long id) {
        return repository.getOne(id);
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    public void editUser(@RequestBody User user, @PathVariable Long id) {

        repository.findById(id)
                .map(oldUser -> {
                    oldUser.setUsername(user.getUsername());
                    oldUser.setRole(user.getRole());
                    return repository.save(oldUser);
                })
                .orElseGet(() -> {
                    user.setId(id);
                    return repository.save(user);
                });
    }

}
