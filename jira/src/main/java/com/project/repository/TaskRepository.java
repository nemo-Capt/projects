package com.project.repository;

import com.project.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Task findByName(String name);

    List<Task> findTasksByUserUsername(String reporter);

    List<Task> findTasksByAssigneeUsername(String assignee);

    List<Task> findByNameContaining(String name);


}
