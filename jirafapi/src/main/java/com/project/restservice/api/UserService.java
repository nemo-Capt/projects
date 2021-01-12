package com.project.restservice.api;

import com.project.entity.User;
import com.project.restservice.dto.RegistrationUserDTO;
import com.project.restservice.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getUserByName(String name);

    User create(UserDTO userDTO);

    User register(RegistrationUserDTO registrationUserDTO);

    void setUserRole(Long id, Long roleId);

}
