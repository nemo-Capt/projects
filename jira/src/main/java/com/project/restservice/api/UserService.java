package com.project.restservice.api;

import com.project.entity.User;
import com.project.restservice.dto.RegistrationUserDTO;
import com.project.restservice.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface UserService {

    Page<UserDTO> getUsers(Pageable pageable);

    User addUser(UserDTO userDTO);

    User registerUser(RegistrationUserDTO userDTO);

    User getUser(Long id);

    List<UserDTO> findByUsernameContains(String username);

    User getUserByName(String username);

    void deleteUser(Long id);

    void editUser(UserDTO user, Long id);

    void setUserRole(Long id, String role);

    void banUser(String username);

    void unbanUser(String username);

}
