package com.project.controller;

import com.project.entity.Task;
import com.project.entity.User;
import com.project.restservice.api.TaskService;
import com.project.restservice.dto.TaskDTO;
import com.project.restservice.dto.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/tasks")
public class TaskController {

    private final TaskService service;

    @Autowired
    public TaskController(TaskService service) {
        this.service = service;
    }


    @PostMapping(consumes = "application/json")
    public void create(@RequestBody Task task) {
        service.addTask(task);
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

    @GetMapping(path = "/name/{name}")
    public TaskDTO findTaskByName(@PathVariable("name") String name) {

        Task task = service.getTaskByName(name);
        TaskDTO taskDTO = TaskMapper.createDTO(task);

        return taskDTO;
    }

    @GetMapping(path = "/assignee/{assignee}")
    public TaskDTO findTaskByAssignee(Pageable pageable, @PathVariable("assignee") String assignee) {

        TaskDTO task = service.getTaskByAssignee(pageable, assignee);

        return task;
    }

    @PutMapping(path = "/{id}")
    public void edit(@RequestBody TaskDTO task, @PathVariable long id) {
        service.editTask(task, id);
    }

    @PutMapping(path = "/addassignee/{id}/assignee")
    public void addAssignee(@PathVariable long id, @RequestParam String assignee) {
        service.assignAssignee(id, assignee);
    }

    @PutMapping(path = "/addreporter/{id}/reporter")
    public void addReporter(@PathVariable long id, @RequestParam String reporter) {
        service.assignReporter(id, reporter);
    }

    @PutMapping(path = "/addstatus/{id}/{statusId}")
    public void setStatus(@PathVariable long id, @PathVariable long statusId) {
        service.setStatus(id, statusId);
    }

    @DeleteMapping(path = "/deletetask/{id}")
    public void delete(@PathVariable("id") long id) {
        service.deleteTask(id);
    }

}
