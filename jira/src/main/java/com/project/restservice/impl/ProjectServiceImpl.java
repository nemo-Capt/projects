package com.project.restservice.impl;

import com.project.EntityNotFoundException;
import com.project.entity.Assignee;
import com.project.entity.Project;
import com.project.entity.Task;
import com.project.entity.User;
import com.project.repository.AssigneeRepository;
import com.project.repository.ProjectRepository;
import com.project.repository.TaskRepository;
import com.project.restservice.api.ProjectService;
import com.project.restservice.dto.ProjectDTO;
import com.project.restservice.dto.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository repository;
    private final TaskRepository taskRepository;
    private final AssigneeRepository assigneeRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository repository, TaskRepository taskRepository, AssigneeRepository assigneeRepository) {
        this.repository = repository;
        this.taskRepository = taskRepository;
        this.assigneeRepository = assigneeRepository;
    }

    @Override
    public Page<ProjectDTO> getProjects(Pageable pageable) {
        return repository.findAll(pageable)
                .map(ProjectMapper::createDTO);
    }

    @Override
    public void addProject(ProjectDTO projectDTO) {

        Project project = new Project();

        project.setName(projectDTO.getName());
        project.setStage("in progress");

        repository.save(project);
    }

    @Override
    public Project getProject(Long id) {
        return repository.getOne(id);
    }

    @Override
    public Project getProjectByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<ProjectDTO> getProjectsByAssignee(Pageable pageable, String assignee) {
        Page<ProjectDTO> projectDTOS = repository.findAll(pageable).map(ProjectMapper::createDTO);
        List<ProjectDTO> newProjects = new ArrayList<>();
        for (ProjectDTO project : projectDTOS) {
            for (int i = 0; i < project.getAssignees().size(); i++) {
                if (project.getAssignees().get(i).equals(assignee)) {
                    newProjects.add(project);
                }
            }
        }
        return newProjects;
    }

    @Override
    public void deleteProject(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void editProject(ProjectDTO project, Long id) {
        repository.findById(id)
                .map(oldProject -> {
                    oldProject.setName(project.getName());
                    oldProject.setStage(project.getStage());
                    oldProject.setDuedate(project.getDuedate());
                    oldProject.setEstimatedtime(project.getEstimatedtime());
                    oldProject.setDesc(project.getDesc());
                    return repository.save(oldProject);
                })
                .orElseThrow(
                        () -> new EntityNotFoundException("Task hasn't been found")
                );
    }

    @Override
    public void assignAssignee(Long id, Long userId) {
        Assignee newAssignee = assigneeRepository.getOne(userId);
        repository.findById(id)
                .map(oldProject -> {
                    List<Assignee> newAssignees = oldProject.getAssignees();
                    newAssignees.add(newAssignee);
                    oldProject.setAssignees(newAssignees);
                    return repository.save(oldProject);
                })
                .orElseThrow(
                        () -> new EntityNotFoundException("Task hasn't been found")
                );
    }

    @Override
    public void addTask(Long id, Long taskId) {
        Task newTask = taskRepository.getOne(taskId);
        repository.findById(id)
                .map(oldProject -> {
                    List<Task> newTasks = oldProject.getTasks();
                    newTasks.add(newTask);
                    oldProject.setTasks(newTasks);
                    return repository.save(oldProject);
                })
                .orElseThrow(
                        () -> new EntityNotFoundException("Task hasn't been found")
                );
    }


}
