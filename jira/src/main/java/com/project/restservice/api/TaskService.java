package com.project.restservice.api;

import com.project.entity.Task;
import com.project.restservice.dto.TaskDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface TaskService {

    Page<TaskDTO> getTasks(Pageable pageable);

    void addTask(Task task);

    Task getTask(Long id);

    void deleteTask(Long id);

    void editTask(Task task, Long id);

    void assignUser(Long id, Long userId);

    void assignReporter(Long id, Long userId);
}
