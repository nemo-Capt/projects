package com.project.restservice.impl;

import com.project.entity.Role;
import com.project.repository.RoleRepository;
import com.project.restservice.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;

    @Autowired
    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Role> getRoles() {
        return repository.findAll();
    }

    @Override
    public Optional<Role> getRole(Long id) {
        return repository.findById(id);
    }
}
