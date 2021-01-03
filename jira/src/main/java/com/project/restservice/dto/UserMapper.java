package com.project.restservice.dto;

import com.project.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDTO createDTO(User user) {
//        if (user == null) {
//            throw new Exception("user not found");
//        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setBanned(user.isBanned());
        userDTO.setUsername(user.getUsername());
        userDTO.setRole(user.getRole().getRolename());

        return userDTO;
    }

    public static List<UserDTO> userDTOs(List<User> users) {
        return users.stream()
                .map(UserMapper::createDTO)
                .collect(Collectors.toList());
    }
}
