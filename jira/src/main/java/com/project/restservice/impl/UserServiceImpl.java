package com.project.restservice.impl;


import com.project.entity.User;
import com.project.repository.UserRepository;
import com.project.restservice.api.UserService;
import com.project.restservice.dto.UserDTO;
import com.project.restservice.dto.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<UserDTO> getUsers(Pageable pageable) {
        return repository.findAll(pageable)
                .map(UserMapper::createDTO);
    }

    @Override
    public void addUser(User user) {
        repository.save(user);
    }

    @Override
    public User getUser(Long id) {
        return repository.getOne(id);
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
                    oldUser.setRole(user.getRole());
                    return repository.save(oldUser);
                })
                .orElseGet(() -> {
                    user.setId(id);
                    return repository.save(user);
                });
    }

}
