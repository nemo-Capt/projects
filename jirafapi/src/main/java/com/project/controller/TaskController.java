package com.project.controller;

import com.project.entity.PageResponse;
import com.project.entity.Task;
import com.project.restservice.api.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    @Autowired
    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity addTask(@RequestBody Task task) {
        try {
            service.addTask(task);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Empty fields");
        }
        return ResponseEntity.status(HttpStatus.OK).body("false");
    }

    @GetMapping
    public ResponseEntity getAll(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "3") int size) {

        PageResponse<Task> users = service.getTasks(page, size);

        return new ResponseEntity(users, HttpStatus.OK);
    }

    @GetMapping(path = "/assignee/{assignee}")
    public List<Task> getTaskByAssignee(@PathVariable String assignee) {

        return service.getTasksByAssignee(assignee);
    }

    @GetMapping(path = "/reporter/{reporter}")
    public List<Task> getTaskByReporter(@PathVariable String reporter) {

        return service.getTasksByReporter(reporter);
    }

    @PutMapping(path = "/{id}")
    public void editTask(@RequestBody Task task, @PathVariable Long id) {

        service.editTask(task, id);
    }

    @PutMapping(path = "/addassignee/{id}/assignee")
    public ResponseEntity addAssignee(@PathVariable Long id, @RequestParam String assignee) {
        try {
            service.assignAssignee(id, assignee);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(false);
    }

    @PutMapping(path = "/addreporter/{id}/reporter")
    public ResponseEntity addReporter(@PathVariable Long id, @RequestParam String reporter) {
        try {
            service.assignReporter(id, reporter);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(false);
    }

    @PutMapping(path = "/addstatus/{id}/{statusid}")
    public void addStatus(@PathVariable Long id, @PathVariable Long statusid) {

        service.setStatus(id, statusid);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id) {
        try {
            service.deleteTask(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Make sure you removed all comments");
        }
        return ResponseEntity.status(HttpStatus.OK).body(false);
    }

}
