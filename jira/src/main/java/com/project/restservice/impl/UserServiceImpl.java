package com.project.restservice.impl;


import com.project.entity.User;
import com.project.repository.UserRepository;
import com.project.restservice.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<User> getUsers() {
        return repository.findAll();
    }

    public void addUser(User user) {
        repository.save(user);
    }

    public Optional<User> getUser(long id) {
        return repository.findById(id);
    }

    public void deleteUser(long id) {
        repository.deleteById(id);
    }

    public void editUser(@RequestBody User user, @PathVariable long id) {

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
