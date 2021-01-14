package com.project.restservice.impl;


import com.project.EntityNotFoundException;
import com.project.entity.Role;
import com.project.entity.User;
import com.project.repository.RoleRepository;
import com.project.repository.UserRepository;
import com.project.restservice.api.UserService;
import com.project.restservice.dto.RegistrationUserDTO;
import com.project.restservice.dto.UserDTO;
import com.project.restservice.dto.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository repository, RoleRepository roleRepository) {
        this.repository = repository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Page<UserDTO> getUsers(Pageable pageable) {
        return repository.findAll(pageable)
                .map(UserMapper::createDTO);
    }

    @Override
    public User addUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setBanned(userDTO.isBanned());
        user.setRole(roleRepository.findByRolename(userDTO.getRole()));

        return repository.save(user);
    }

    @Override
    public User registerUser(RegistrationUserDTO userDTO) {

        User user = new User();
        user.setId(4);
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setBanned(false);
        user.setRole(roleRepository.findByRolename("user"));

        return repository.save(user);
    }

    @Override
    public User getUser(Long id) {
        return repository.getOne(id);
    }

    @Override
    public User getUserByName(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void editUser(User user, Long id) {

        repository.findById(id)
                .map(oldUser -> {
                    oldUser.setUsername(user.getUsername());
                    oldUser.setEmail(user.getEmail());
                    oldUser.setPassword(user.getPassword());
                    oldUser.setBanned(user.isBanned());
                    return repository.save(oldUser);
                })
                .orElseThrow(
                        () -> new EntityNotFoundException("Task hasn't been found")
                );
    }

    @Override
    public void setUserRole(Long id, Long roleId) {
        Role newRole = roleRepository.getOne(roleId);
        repository.findById(id)
                .map(oldUser -> {
                    oldUser.setRole(newRole);
                    return repository.save(oldUser);
                })
                .orElseThrow(
                        () -> new EntityNotFoundException("Task hasn't been found")
                );
    }

}
