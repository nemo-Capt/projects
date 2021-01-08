package com.project.restservice.dto;

import com.project.EntityNotFoundException;
import com.project.entity.Project;

import java.util.List;
import java.util.stream.Collectors;

public class ProjectMapper {

    public static ProjectDTO createDTO(Project project) {
        if (project == null) {
            throw new EntityNotFoundException("Task hasn't been found");
        }
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());
        projectDTO.setStage(project.getStage());
        projectDTO.setDuedate(project.getDuedate());
        projectDTO.setEstimatedtime(project.getEstimatedtime());
        projectDTO.setDesc(project.getDesc());
        projectDTO.setTasks(project.getTasks());
        projectDTO.setAssignees(project.getAssignees());
        return projectDTO;
    }

    public static List<ProjectDTO> projectDTOS(List<Project> projects) {
        return projects.stream()
                .map(ProjectMapper::createDTO)
                .collect(Collectors.toList());
    }
}
