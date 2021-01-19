package com.project.repository;

import com.project.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Task findByName(String name);

    Task findByAssignee(String assignee);

}
