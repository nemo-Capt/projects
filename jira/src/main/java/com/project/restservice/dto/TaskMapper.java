package com.project.restservice.dto;

import com.project.EntityNotFoundException;
import com.project.entity.Task;

import java.util.List;
import java.util.stream.Collectors;

public class TaskMapper {

    public static TaskDTO createDTO(Task task) {
        if (task == null) {
            throw new EntityNotFoundException("Task hasn't been found");
        }
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setUser(task.getUser().getUsername());
        taskDTO.setAssignee(task.getAssignee().getUser().getUsername());
        taskDTO.setProject(task.getProject().getName());
        taskDTO.setName(task.getName());
        taskDTO.setDesc(task.getDesc());
        taskDTO.setPriority(task.getPriority());
        taskDTO.setDuedate(task.getDuedate());
        taskDTO.setEstimatedtime(task.getEstimatedtime());
        taskDTO.setStatus(task.getStatus().getStatus());
        taskDTO.setComments(task.getComments());
        return taskDTO;
    }

    public static List<TaskDTO> userDTOs(List<Task> tasks) {
        return tasks.stream()
                .map(TaskMapper::createDTO)
                .collect(Collectors.toList());
    }
}
