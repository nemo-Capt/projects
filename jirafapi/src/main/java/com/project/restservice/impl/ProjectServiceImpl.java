package com.project.restservice.impl;

import com.project.config.Constants;
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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    public void addProject(Project projectDTO) {

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

    }

    @Override
    public void assignAssignee(Long id, Long userId) {

    }

    @Override
    public void addTask(Long id, Long taskId) {

    }

    @Override
    public Project getProjectByName(String name) {
        return restTemplate.getForObject(Constants.PROJECT_URL + "/name/" + name, Project.class);
    }

//    @Override
//    public PageResponse<Project> getProjectsByAssignees(List<String> assignees, int page, int size) {
//        PageResponse pageResponse = restTemplate.getForObject(
//                Constants.PROJECT_URL + "?page=" + page + "&size=" + size,
//                PageResponse.class
//        );
//        return pageResponse;
//    }
}
