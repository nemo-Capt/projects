package com.project.restservice.dto;

import com.project.EntityNotFoundException;
import com.project.entity.Assignee;
import com.project.entity.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
        List<String> tasks = new ArrayList<>();
        for (int i = 0; i < project.getTasks().size(); i++) {
            tasks.add(project.getTasks().get(i).getName());
        }
        projectDTO.setTasks(tasks);
        List<String> assignees = new ArrayList<>();
        for (int i = 0; i < project.getAssignees().size(); i++) {
            assignees.add(project.getAssignees().get(i).getUser().getUsername());
        }
        projectDTO.setAssignees(assignees);
        return projectDTO;
    }

    public static List<ProjectDTO> projectDTOS(List<Project> projects) {
        return projects.stream()
                .map(ProjectMapper::createDTO)
                .collect(Collectors.toList());
    }
}
