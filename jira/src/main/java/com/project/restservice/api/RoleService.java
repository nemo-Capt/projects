package com.project.restservice.api;

import com.project.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<Role> getRoles();

    Optional<Role> getRole(Long id);

}
