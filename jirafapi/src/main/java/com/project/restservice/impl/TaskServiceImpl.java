package com.project.restservice.impl;

import com.project.config.Constants;
import com.project.controller.ApiResponse;
import com.project.entity.PageResponse;
import com.project.entity.Project;
import com.project.entity.Task;
import com.project.restservice.api.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


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
        restTemplate.postForEntity(Constants.TASK_URL, task, ApiResponse.class).getBody();
    }

    @Override
    public Task getTask(Long id) {
        return null;
    }

    @Override
    public List<Task> getTasksByAssignee(String assignee) {

        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(
                Constants.TASK_URL + "/assignee/" + assignee, Task[].class)));
    }

    @Override
    public List<Task> getTasksByReporter(String reporter) {

        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(
                Constants.TASK_URL + "/reporter/" + reporter, Task[].class)));
    }

    @Override
    public void deleteTask(Long id) {
        restTemplate.delete(Constants.TASK_URL + "/" + id);
    }

    @Override
    public void editTask(Task task, Long id) {
        Task newTask = new Task();
        newTask.setName(task.getName());
        newTask.setDesc(task.getDesc());
        newTask.setPriority(task.getPriority());
        newTask.setDuedate(task.getDuedate());
        newTask.setEstimatedtime(task.getEstimatedtime());
        newTask.setStatus(task.getStatus());
        restTemplate.put(
                Constants.TASK_URL + "/" + id,
                newTask
        );
    }

    @Override
    public void assignAssignee(Long id, String assignee) {
        restTemplate.put(
                Constants.TASK_URL + "/addassignee/" + id + "/assignee?assignee=" + assignee,
                null,
                ApiResponse.class);
    }

    @Override
    public void assignReporter(Long id, String reporter) {
        restTemplate.put(
                Constants.TASK_URL + "/addreporter/" + id + "/reporter?reporter=" + reporter,
                null,
                ApiResponse.class);
    }

    @Override
    public void setStatus(Long id, Long statusId) {
        restTemplate.put(
                Constants.TASK_URL + "/addstatus/" + id + "/" + statusId,
                null,
                ApiResponse.class);
    }
}
