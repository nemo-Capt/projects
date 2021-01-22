package com.project.restservice.api;

import com.project.entity.PageResponse;
import com.project.entity.Task;

public interface TaskService {

    PageResponse<Task> getTasks(int page, int size);

    void addTask(Task task);

    Task getTask(Long id);

    Task getTaskBtAssignee(String assignee);

    void deleteTask(Long id);

    void editTask(Task task, Long id);

    void assignAssignee(Long id, String assignee);

    void assignReporter(Long id, String reporter);

    void setStatus(Long id, Long statusId);
}
