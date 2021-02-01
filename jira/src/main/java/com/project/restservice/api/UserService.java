package com.project.restservice.api;

import com.project.entity.User;
import com.project.restservice.dto.RegistrationUserDTO;
import com.project.restservice.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserService {

    Page<UserDTO> getUsers(Pageable pageable);

    User addUser(UserDTO userDTO);

    User registerUser(RegistrationUserDTO userDTO);

    User getUser(Long id);

    User getUserByName(String username);

    void deleteUser(Long id);

    void editUser(User user, Long id);

    void setUserRole(Long id, Long roleId);

    void banUser(String username);

    void unbanUser(String username);
}
