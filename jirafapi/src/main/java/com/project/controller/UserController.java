package com.project.controller;


import com.project.controller.emailvalidation.EmailValidation;
import com.project.entity.PageResponse;
import com.project.entity.User;
import com.project.restservice.api.UserService;
import com.project.restservice.dto.EditDTO;
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


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;
    private final EmailValidation emailValidation;

    @Autowired
    public UserController(UserService service, EmailValidation emailValidation) {
        this.service = service;
        this.emailValidation = emailValidation;
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

    @PutMapping("/setrole")
    public void changeRole(@RequestBody User user) {

        service.setUserRole(user.getId(), user.getRole());
    }

    @GetMapping("/ban/{username}")
    public void ban(@PathVariable String username) {
        service.banUser(username);
    }

    @GetMapping("/unban/{username}")
    public void unban(@PathVariable String username) {
        service.unbanUser(username);
    }

    @PutMapping("/edit")
    public ResponseEntity edit(@RequestBody EditDTO editDTO) {
        if (editDTO.getUsername() == null || editDTO.getUsername().equals("")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Type in username");
        }
        if (!emailValidation.validate(editDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid email");
        }
        if (editDTO.getNewPassword() != null && editDTO.getNewPassword().length() < 4) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Password must be at least 4 characters long");
        }
        try {
            service.edit(editDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Wrong password");
        }
        return ResponseEntity.status(HttpStatus.OK).body(false);
    }

}
