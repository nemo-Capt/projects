package com.project.restservice.api;

import com.project.entity.PageResponse;
import com.project.entity.Task;

import java.util.List;

public interface TaskService {

    PageResponse<Task> getTasks(int page, int size);

    void addTask(Task task);

    Task getTask(Long id);

    List<Task> getTasksByNameContaining(String name);

    List<Task> getTasksByAssignee(String assignee);

    List<Task> getTasksByReporter(String reporter);

    List<Task> getTasksByProjects(List<String> projects);

    void deleteTask(Long id);

    void pmDelete(Long id);

    void editTask(Task task, Long id);

    void assignAssignee(Long id, String assignee);

    void assignReporter(Long id, String reporter);

    void setStatus(Long id, Long statusId);
}
