package com.project.restservice;

import com.project.entity.User;
import com.project.repository.UserRepository;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> findAll() {
        return service.getUsers();
    }

    @GetMapping(path = "/{user}")
    public User find(@PathVariable("id") long id) {
        return service.getUser(id);
    }

    @PostMapping(consumes = "application/json")
    public void create(@RequestBody User user) {
        service.addUser(user);
    }

    @DeleteMapping(path = "/{user}")
    public void delete(@PathVariable("id") long id) {
        service.deleteUser(id);
    }

    /*@PutMapping(path = "/{user}")
    public User update(@PathVariable("id") long id, @RequestBody User user) throws BadHttpRequest {
        if (repository.exists(id)) {
            user.setSurname(surname);
            return repository.save(user);
        } else {
            throw new BadHttpRequest();
        }
    }*/

}

