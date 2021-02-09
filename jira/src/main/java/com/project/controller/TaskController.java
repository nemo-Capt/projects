package com.project.controller;

import com.project.entity.Task;
import com.project.entity.User;
import com.project.restservice.api.TaskService;
import com.project.restservice.dto.TaskDTO;
import com.project.restservice.dto.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;


@RestController
@RequestMapping(path = "/tasks")
public class TaskController {

    private final TaskService service;

    @Autowired
    public TaskController(TaskService service) {
        this.service = service;
    }


    @PostMapping(consumes = "application/json")
    public void create(@RequestBody TaskDTO taskDTO) {
        
        service.addTask(taskDTO);
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
    public List<TaskDTO> findTaskByAssignee(@PathVariable("assignee") String assignee) {

        List<TaskDTO> tasks = service.getTasksByAssignee(assignee);

        return tasks;
    }

    @GetMapping(path = "/reporter/{reporter}")
    public List<TaskDTO> findTaskByReporter(@PathVariable("reporter") String reporter) {

        List<TaskDTO> tasks = service.getTaskByReporter(reporter);

        return tasks;
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
    public ResponseEntity<ApiResponse> addReporter(@PathVariable long id, @RequestParam String reporter) {
        try {
            service.assignReporter(id, reporter);
        } catch (HttpServerErrorException e) {
            return new ResponseEntity<>(HttpStatus.resolve(404));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/addstatus/{id}/{statusId}")
    public void setStatus(@PathVariable long id, @PathVariable long statusId) {
        service.setStatus(id, statusId);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") long id) {
        service.deleteTask(id);
    }

}
