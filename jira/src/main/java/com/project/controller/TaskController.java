package com.project.controller;

import com.project.entity.Task;
import com.project.entity.User;
import com.project.restservice.api.TaskService;
import com.project.restservice.dto.TaskDTO;
import com.project.restservice.dto.TaskMapper;
import com.project.restservice.dto.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/tasks")
public class TaskController {

    private final TaskService service;

    @Autowired
    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public Page<TaskDTO> findAll(Pageable pageable) {

        Page<TaskDTO> taskDTOS = service.getTasks(pageable);
        return taskDTOS;
    }

    @GetMapping(path = "/{id}")
    public TaskDTO find(@PathVariable("id") Long id) {

        Task task = service.getTask(id);
        TaskDTO taskDTO = TaskMapper.createDTO(task);

        return taskDTO;
    }

}
