package com.project.controller;


import com.project.entity.PageResponse;
import com.project.entity.Project;
import com.project.restservice.api.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PostMapping(path = "/{assignee}")
    public ResponseEntity addProject(@RequestBody Project project, @PathVariable String assignee) {
        try {
            service.addProject(project, assignee);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Type in project name");
        }
        return ResponseEntity.status(HttpStatus.OK).body(false);
    }


    @GetMapping("/name/{name}")
    public Project getProjectByName(@PathVariable(name = "name") String name) {

        Project project = service.getProjectByName(name);

        return project;
    }

    @GetMapping("/assignee/{assignee}")
    public ResponseEntity getProjectByAssignee(@PathVariable String assignee) {

        List<Project> projects = service.getProjectsByAssignee(assignee);

        return new ResponseEntity(projects, HttpStatus.OK);
    }

    @GetMapping("/unassigned")
    public List<Project> getUnassignedProjects() {
        List<Project> projects = service.getUnassignedProjects();

        return projects;
    }

    @PutMapping(path = "/{id}")
    public void editProject(@RequestBody Project project, @PathVariable Long id) {

        service.editProject(project, id);
    }

    @PutMapping(path = "/addassignee/{id}/{assignee}")
    public ResponseEntity addAssignee(@PathVariable Long id, @PathVariable String assignee) {
        try {
            service.assignAssignee(id, assignee);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(false);
    }

    @DeleteMapping(path = "/deleteassignee/{projectId}/{username}")
    public void deleteAssignee(@PathVariable Long projectId, @PathVariable String username) {
        service.removeAssignee(projectId, username);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProject(@PathVariable Long id) {
        try {
            service.deleteProject(id);
        } catch (HttpServerErrorException e) {
            return new ResponseEntity<>(null, HttpStatus.resolve(511));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
