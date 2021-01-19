package com.project.restservice.api;

import com.project.entity.PageResponse;
import com.project.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProjectService {

    PageResponse<Project> getProjects(int page, int size);

    void addProject(Project project);

    Project getProject(Long id);

    void deleteProject(Long id);

    void editProject(Project project, Long id);

    void assignAssignee(Long id, Long userId);

    void addTask(Long id, Long taskId);

    Project getProjectByName(String name);

//    PageResponse<Project> getProjectsByAssignees(List<String> assignee, int page, int size);
}
