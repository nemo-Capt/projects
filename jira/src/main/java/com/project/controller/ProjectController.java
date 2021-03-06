package com.project.controller;


import com.project.entity.Project;
import com.project.restservice.api.ProjectService;
import com.project.restservice.dto.ProjectDTO;
import com.project.restservice.dto.ProjectMapper;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/projects")
public class ProjectController {

    private final ProjectService service;

    @Autowired
    public ProjectController(ProjectService service) {
        this.service = service;
    }


    @PostMapping(path = "/{assignee}", consumes = "application/json")
    public void create(@RequestBody ProjectDTO projectDTO, @PathVariable String assignee) {
        service.addProject(projectDTO, assignee);
    }

    @GetMapping
    public Page<ProjectDTO> findAll(Pageable pageable) {

        Page<ProjectDTO> projectDTOS = service.getProjects(pageable);
        return projectDTOS;
    }

    @GetMapping(path = "/{id}")
    public ProjectDTO find(@PathVariable("id") Long id) {

        Project project = service.getProject(id);
        ProjectDTO projectDTO = ProjectMapper.createDTO(project);

        return projectDTO;
    }

    @GetMapping(path = "/name/{name}")
    public ProjectDTO findByName(@PathVariable("name") String name) {

        Project project = service.getProjectByName(name);
        ProjectDTO projectDTO = ProjectMapper.createDTO(project);

        return projectDTO;
    }

    @GetMapping(path = "/assignee/{assignee}")
    public List<ProjectDTO> findByAssignee(Pageable pageable, @PathVariable String assignee) {

        List<ProjectDTO> projects = service.getProjectsByAssignee(pageable, assignee);

        return projects;
    }

    @GetMapping(path = "/unassigned")
    public List<ProjectDTO> getUnassignedProjects(Pageable pageable) {

        List<ProjectDTO> projects = service.getUnassignedProjects(pageable);

        return projects;
    }

    @PutMapping(path = "/{id}")
    public void edit(@RequestBody ProjectDTO project, @PathVariable long id) {
        service.editProject(project, id);
    }

    @PutMapping(path = "/addassignee/{id}/{assignee}")
    public ResponseEntity addAssignee(@PathVariable long id, @PathVariable String assignee) {
        try {
            service.assignAssignee(id, assignee);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.valueOf(500)).body(false);
        }
        return ResponseEntity.status(HttpStatus.OK).body(false);
    }

    @PutMapping(path = "/addtask/{id}/{taskId}")
    public void addTask(@PathVariable long id, @PathVariable long taskId) {
        service.addTask(id, taskId);
    }

    @DeleteMapping(path = "/deleteassignee/{projectId}/{username}")
    public void deleteAssignee(@PathVariable Long projectId, @PathVariable String username) {
        service.removeAssignee(projectId, username);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") long id) {
        try {
            service.deleteProject(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.resolve(511));
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
