package com.project.restservice.api;

import com.project.entity.User;
import com.project.restservice.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


public interface UserService {

    Page<UserDTO> getUsers(Pageable pageable);

    void addUser(User user);

    User getUser(Long id);

    void deleteUser(Long id);

    void editUser(@RequestBody User user, @PathVariable Long id);

}
