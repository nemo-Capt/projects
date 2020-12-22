package com.project.restservice;


import com.project.entity.User;
import com.project.repository.UserRepository;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
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

    public User editUser(@RequestBody User user, @PathVariable long id) {

        return repository.findById(id)
                .map(employee -> {
                    employee.setUsername(user.getUsername());
                    employee.setRole(user.getRole());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    user.setId(id);
                    return repository.save(user);
                });
    }

}
