package com.project.restservice.api;

import com.project.entity.Task;
import com.project.entity.User;
import com.project.restservice.dto.TaskDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TaskService {

    Page<TaskDTO> getTasks(Pageable pageable);

    void addTask(Task task);

    Task getTask(Long id);

    void deleteTask(Long id);

    void editTask(Task task, Long id);

    void assignUser(Task task, Long id, User user);

    void assignReporter(Task task, Long id, User user);
}
