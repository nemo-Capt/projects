package com.project.controller;

import com.project.entity.Task;
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

    @GetMapping(path = "/contains/{name}")
    public List<TaskDTO> getTasksByNameContaining(@PathVariable String name) {

        List<TaskDTO> taskDTOs = service.getByNameContaining(name);

        return taskDTOs;
    }


    @GetMapping(path = "/project/{projects}")
    public List<TaskDTO> getTasksByProject(@PathVariable("projects") List<String> projects) {

        List<TaskDTO> taskDTOs = service.getTasksByProjects(projects);

        return taskDTOs;
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
    public ResponseEntity addAssignee(@PathVariable long id, @RequestParam String assignee) {
        try {
            service.assignAssignee(id, assignee);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.valueOf(500)).body("User not found");
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping(path = "/addreporter/{id}/reporter")
    public ResponseEntity addReporter(@PathVariable long id, @RequestParam String reporter) {
        try {
            service.assignReporter(id, reporter);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.valueOf(500)).body("User not found");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/addstatus/{id}/{statusId}")
    public void setStatus(@PathVariable long id, @PathVariable long statusId) {
        service.setStatus(id, statusId);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable("id") long id) {
        try {
            service.deleteTask(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.valueOf(500)).body("User already exists");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/pm/{id}")
    public void pmDelete(@PathVariable Long id) {
        service.pmDeleteTask(id);
    }

}
