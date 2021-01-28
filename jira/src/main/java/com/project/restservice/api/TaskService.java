package com.project.restservice.api;

import com.project.entity.Task;
import com.project.restservice.dto.TaskDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface TaskService {

    Page<TaskDTO> getTasks(Pageable pageable);

    void addTask(Task task);

    Task getTask(Long id);

    Task getTaskByName(String name);

    List<TaskDTO> getTasksByAssignee(String assignee);

    List<TaskDTO> getTaskByReporter(String reporter);

    void deleteTask(Long id);

    void editTask(TaskDTO task, Long id);

    void assignAssignee(Long id, String assignee);

    void assignReporter(Long id, String reporter);

    void setStatus(Long id, Long statusId);
}
