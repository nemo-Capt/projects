package com.project.restservice.impl;

import com.project.config.Constants;
import com.project.entity.PageResponse;
import com.project.entity.Task;
import com.project.restservice.api.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;


@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final RestTemplate restTemplate;

    @Autowired
    public TaskServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public PageResponse<Task> getTasks(int page, int size) {
        PageResponse pageResponse =
                restTemplate.getForObject(
                        Constants.TASK_URL + "?page=" + page + "&size=" + size,
                        PageResponse.class
                );
        return pageResponse;
    }

    @Override
    public void addTask(Task task) {

    }

    @Override
    public Task getTask(Long id) {
        return null;
    }

    @Override
    public void deleteTask(Long id) {

    }

    @Override
    public void editTask(Task task, Long id) {

    }

    @Override
    public void assignAssignee(Long id, Long userId) {

    }

    @Override
    public void assignReporter(Long id, Long userId) {

    }

    @Override
    public void setStatus(Long id, Long statusId) {

    }
}
