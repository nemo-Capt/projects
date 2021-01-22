package com.project.restservice.api;

import com.project.entity.Task;
import com.project.restservice.dto.TaskDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface TaskService {

    Page<TaskDTO> getTasks(Pageable pageable);

    void addTask(Task task);

    Task getTask(Long id);

    Task getTaskByName(String name);

    TaskDTO getTaskByAssignee(Pageable pageable, String assignee);

    void deleteTask(Long id);

    void editTask(TaskDTO task, Long id);

    void assignAssignee(Long id, String assignee);

    void assignReporter(Long id, String reporter);

    void setStatus(Long id, Long statusId);
}
