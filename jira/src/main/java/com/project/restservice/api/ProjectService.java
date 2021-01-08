package com.project.restservice.api;

import com.project.entity.Project;
import com.project.entity.Task;
import com.project.restservice.dto.ProjectDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectService {

    Page<ProjectDTO> getProjects(Pageable pageable);

    void addProject(Project project);

    Project getProject(Long id);

    void deleteProject(Long id);

    void editProject(Project project, Long id);

    void assignAssignee(Long id, Long userId);

    void addTask(Long id, Long taskId);
}
