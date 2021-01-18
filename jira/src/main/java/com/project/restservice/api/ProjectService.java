package com.project.restservice.api;

import com.project.entity.Project;
import com.project.entity.Task;
import com.project.restservice.dto.ProjectDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectService {

    Page<ProjectDTO> getProjects(Pageable pageable);

    void addProject(ProjectDTO projectDTO);

    Project getProject(Long id);

    void deleteProject(Long id);

    void editProject(Project project, Long id);

    void assignAssignee(Long id, Long userId);

    void addTask(Long id, Long taskId);

    Project getProjectByName(String name);

//    Project getProjectByAssignees(List<String> );
}
