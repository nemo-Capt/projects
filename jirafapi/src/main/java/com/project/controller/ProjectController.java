package com.project.controller;


import com.project.entity.PageResponse;
import com.project.entity.Project;
import com.project.entity.User;
import com.project.restservice.api.ProjectService;
import com.project.restservice.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService service;

    @Autowired
    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity getAll(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "100") int size) {

        PageResponse<Project> projects = service.getProjects(page, size);

        return new ResponseEntity(projects, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public Project getProjectByName(@PathVariable(name = "name") String name) {

        Project project = service.getProjectByName(name);

        return project;
    }

//    @GetMapping("/assignee")
//    public ResponseEntity getProjectByAssignee(@RequestParam List<String> assignees,
//                                               @RequestParam(defaultValue = "0") int page,
//                                               @RequestParam(defaultValue = "100") int size) {
//
//        PageResponse<Project> projects = service.getProjectsByAssignees(assignees, page, size);
//        //List<Project> projects = service.getProjectsByAssignees(assignees);
//
//        return new ResponseEntity(projects, HttpStatus.OK);
//    }
}
