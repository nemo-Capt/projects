package com.project.restservice.api;

import com.project.entity.PageResponse;
import com.project.entity.User;
import com.project.restservice.dto.EditDTO;
import com.project.restservice.dto.RegistrationUserDTO;
import com.project.restservice.dto.UserDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    PageResponse<User> getAll(int page, int size);

    User getUserByName(String name);

    List<User> getByUsernameContains(String username);

    User create(UserDTO userDTO);

    User register(RegistrationUserDTO registrationUserDTO);

    void edit(EditDTO editDTO) throws Exception;

    void setUserRole(Long id, String role);

    void banUser(String username);

    void unbanUser(String username);

}
