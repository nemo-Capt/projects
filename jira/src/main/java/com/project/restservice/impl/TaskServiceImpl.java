package com.project.restservice.impl;

import com.project.entity.Task;
import com.project.entity.User;
import com.project.repository.TaskRepository;
import com.project.repository.TaskRepository;
import com.project.restservice.api.TaskService;
import com.project.restservice.dto.TaskDTO;
import com.project.restservice.dto.TaskMapper;
import com.project.restservice.dto.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;

    @Autowired
    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<TaskDTO> getTasks(Pageable pageable) {
        return repository.findAll(pageable)
                .map(TaskMapper::createDTO);
    }

    @Override
    public void addTask(Task task) {
        repository.save(task);
    }

    @Override
    public Task getTask(Long id) {
        return repository.getOne(id);
    }

    @Override
    public void deleteTask(Long id) {

    }

    @Override
    public void editTask(Task task, Long id) {

    }

    @Override
    public void assignUser(Task task, Long id, User user) {

    }

    @Override
    public void assignReporter(Task task, Long id, User user) {

    }
}
