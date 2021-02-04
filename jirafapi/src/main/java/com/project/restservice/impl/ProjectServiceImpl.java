package com.project.restservice.impl;

import com.project.config.Constants;
import com.project.controller.ApiResponse;
import com.project.entity.PageResponse;
import com.project.entity.Project;
import com.project.restservice.api.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import sun.security.krb5.internal.APOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private final RestTemplate restTemplate;

    @Autowired
    public ProjectServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public PageResponse<Project> getProjects(int page, int size) {

        PageResponse pageResponse =
                restTemplate.getForObject(
                        Constants.PROJECT_URL + "?page=" + page + "&size=" + size,
                        PageResponse.class
                );
        return pageResponse;
    }

    @Override
    public void addProject(Project project, String assignee) {

        restTemplate.postForEntity(Constants.PROJECT_URL + "/" + assignee, project, ApiResponse.class).getBody();
    }

    @Override
    public Project getProject(Long id) {
        return null;
    }

    @Override
    public void deleteProject(Long id) {

    }

    @Override
    public void editProject(Project project, Long id) {
        Project newProject = new Project();
        newProject.setName(project.getName());
        newProject.setDesc(project.getDesc());
        newProject.setStage(project.getStage());
        newProject.setDuedate(project.getDuedate());
        newProject.setEstimatedtime(project.getEstimatedtime());
        restTemplate.put(
                Constants.PROJECT_URL + "/" + id,
                newProject
        );
    }

    @Override
    public void assignAssignee(Long id, String assignee) {
        restTemplate.put(Constants.PROJECT_URL + "/addassignee/" + id + "/" + assignee, ApiResponse.class);
    }

    @Override
    public void removeAssignee(Long projectId, String username) {
        restTemplate.delete(Constants.PROJECT_URL + "/deleteassignee/" + projectId + "/" + username);
    }

    @Override
    public void addTask(Long id, Long taskId) {

    }

    @Override
    public Project getProjectByName(String name) {
        return restTemplate.getForObject(Constants.PROJECT_URL + "/name/" + name, Project.class);
    }

    @Override
    public List<Project> getProjectsByAssignee(String assignee) {
        List<Project> projects = Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(
                Constants.PROJECT_URL + "/assignee/" + assignee, Project[].class)));
        System.out.println(projects.get(0));
        return projects;
    }
}
