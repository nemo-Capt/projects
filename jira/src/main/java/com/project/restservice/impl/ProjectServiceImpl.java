package com.project.restservice.impl;

import com.project.EntityNotFoundException;
import com.project.entity.Assignee;
import com.project.entity.Project;
import com.project.entity.Task;
import com.project.entity.User;
import com.project.repository.AssigneeRepository;
import com.project.repository.ProjectRepository;
import com.project.repository.TaskRepository;
import com.project.repository.UserRepository;
import com.project.restservice.api.ProjectService;
import com.project.restservice.dto.ProjectDTO;
import com.project.restservice.dto.ProjectMapper;
import com.project.restservice.mail.Mail;
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
    private final UserRepository userRepository;
    private final Mail mail;

    @Autowired
    public ProjectServiceImpl(ProjectRepository repository, TaskRepository taskRepository,
                              AssigneeRepository assigneeRepository, UserRepository userRepository, Mail mail) {
        this.repository = repository;
        this.taskRepository = taskRepository;
        this.assigneeRepository = assigneeRepository;
        this.userRepository = userRepository;
        this.mail = mail;
    }

    @Override
    public Page<ProjectDTO> getProjects(Pageable pageable) {
        return repository.findAll(pageable)
                .map(ProjectMapper::createDTO);
    }

    @Override
    public void addProject(ProjectDTO projectDTO, String assignee) {

        Project project = new Project();
        Assignee newAssignee = new Assignee();

        project.setName(projectDTO.getName());
        project.setStage("to do");
        project.setEstimatedtime(projectDTO.getEstimatedtime());

        newAssignee.setProject(project);
        newAssignee.setUser(userRepository.findByUsername(assignee));

        repository.save(project);
        assigneeRepository.save(newAssignee);
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
    public List<ProjectDTO> getUnassignedProjects(Pageable pageable) {
        Page<ProjectDTO> projectDTOS = repository.findAll(pageable).map(ProjectMapper::createDTO);
        List<ProjectDTO> newProjects = new ArrayList<>();
        for (ProjectDTO project : projectDTOS) {
            if (project.getAssignees().isEmpty()) {
                newProjects.add(project);
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
                    List<Task> tasks = oldProject.getTasks();
                    for (Task task : tasks) {
                        task.setName(project.getName().concat(task.getName().substring(task.getName().indexOf('('))));
                    }
                    oldProject.setTasks(tasks);
                    return repository.save(oldProject);
                })
                .orElseThrow(
                        () -> new EntityNotFoundException("Task hasn't been found")
                );
    }

    @Override
    public void assignAssignee(Long id, String assignee) {
        Assignee newAssignee = new Assignee();
        repository.findById(id)
                .map(oldProject -> {
                    List<Assignee> newAssignees = oldProject.getAssignees();
                    boolean isPresent = false;
                    for (Assignee i : newAssignees) {
                        if (i.getUser().getUsername().equals(assignee)) {
                            isPresent = true;
                            break;
                        }
                    }

                    if (!isPresent) {
                        newAssignee.setProject(repository.getOne(id));
                        newAssignee.setUser(userRepository.findByUsername(assignee));
                        assigneeRepository.save(newAssignee);
                        newAssignees.add(newAssignee);
                        oldProject.setAssignees(newAssignees);
                    }
                    return repository.save(oldProject);
                })
                .orElseThrow(
                        () -> new EntityNotFoundException("Task hasn't been found")
                );
    }

    @Override
    public void removeAssignee(Long id, String assignee) {
        assigneeRepository.deleteByUserUsernameAndProjectId(assignee, id);
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
