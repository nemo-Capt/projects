package com.project.restservice.dto;

import com.project.entity.Task;

import java.util.List;
import java.util.stream.Collectors;

public class TaskMapper {

    public static TaskDTO createDTO(Task task) {
//        if (user == null) {
//            throw new Exception("user not found");
//        }
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setUser(task.getUser().getUsername());
        taskDTO.setAssignee(task.getAssignee().getUser().getUsername());
        taskDTO.setProject(task.getProject().getName());
        taskDTO.setName(task.getName());
        taskDTO.setDesc(task.getDesc());
        taskDTO.setProject(task.getProject().getName());
        taskDTO.setDuedate(task.getDuedate());
        taskDTO.setEstimatedtime(task.getEstimatedtime());
        taskDTO.setStatus(task.getStatus().getStatus());
        return taskDTO;
    }

    public static List<TaskDTO> userDTOs(List<Task> tasks) {
        return tasks.stream()
                .map(TaskMapper::createDTO)
                .collect(Collectors.toList());
    }
}
