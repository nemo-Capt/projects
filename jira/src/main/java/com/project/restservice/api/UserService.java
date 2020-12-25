package com.project.restservice.api;

import com.project.entity.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getUsers();

    void addUser(User user);

    Optional<User> getUser(long id);

    void deleteUser(long id);

    void editUser(@RequestBody User user, @PathVariable long id);

}
