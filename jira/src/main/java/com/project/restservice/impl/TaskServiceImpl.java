package com.project.restservice.impl;

import com.project.EntityNotFoundException;
import com.project.entity.Status;
import com.project.entity.Task;
import com.project.entity.User;
import com.project.repository.ProjectRepository;
import com.project.repository.StatusRepository;
import com.project.repository.TaskRepository;
import com.project.repository.UserRepository;
import com.project.restservice.api.TaskService;
import com.project.restservice.dto.TaskDTO;
import com.project.restservice.dto.TaskMapper;
import com.project.restservice.mail.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final StatusRepository statusRepository;
    private final Mail mail;

    @Autowired
    public TaskServiceImpl(TaskRepository repository, UserRepository userRepository,
                           ProjectRepository projectRepository, StatusRepository statusRepository, Mail mail) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.statusRepository = statusRepository;
        this.mail = mail;
    }

    @Override
    public Page<TaskDTO> getTasks(Pageable pageable) {
        return repository.findAll(pageable)
                .map(TaskMapper::createDTO);
    }

    @Override
    public void addTask(TaskDTO taskDTO) {
        Task task = new Task();
        Random random = new Random();
        int code = random.nextInt(9999 - 1000) + 1000;
        task.setName(taskDTO.getProject() + "(" + code + ")-" + taskDTO.getName());
        task.setPriority("low");
        task.setStatus(statusRepository.findByStatus(taskDTO.getStatus()));
        task.setProject(projectRepository.findByName(taskDTO.getProject()));
        task.setUser(userRepository.findByUsername(taskDTO.getUser()));
        if (task.getUser() != null) {
            mail.sendEmailToReporter(task.getUser().getEmail(), task.getName());
        }
        task.setAssignee(userRepository.findByUsername(taskDTO.getAssignee()));
        if (task.getAssignee() != null) {
            mail.sendEmailToAssignee(task.getAssignee().getEmail(), task.getName());
        }
        task.setEstimatedtime(taskDTO.getEstimatedtime());

        repository.save(task);
    }

    @Override
    public Task getTask(Long id) {
        return repository.getOne(id);
    }

    @Override
    public Task getTaskByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<TaskDTO> getTasksByAssignee(String assignee) {
        List<Task> tasks = repository.findTasksByAssigneeUsername(assignee);
        List<TaskDTO> newTasks = new ArrayList<>();
        for (Task task : tasks) {
            newTasks.add(TaskMapper.createDTO(task));
        }
        return newTasks;
    }

    @Override
    public List<TaskDTO> getTaskByReporter(String reporter) {
        List<Task> tasks = repository.findTasksByUserUsername(reporter);
        List<TaskDTO> newTasks = new ArrayList<>();
        for (Task task : tasks) {
            newTasks.add(TaskMapper.createDTO(task));
        }
        return newTasks;
    }

    @Override
    public void deleteTask(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void editTask(TaskDTO task, Long id) {
        repository.findById(id)
                .map(oldTask -> {
                    oldTask.setName(task.getName());
                    oldTask.setDesc(task.getDesc());
                    oldTask.setPriority(task.getPriority());
                    oldTask.setDuedate(task.getDuedate());
                    oldTask.setEstimatedtime(task.getEstimatedtime());
                    oldTask.setStatus(statusRepository.findByStatus(task.getStatus()));
                    return repository.save(oldTask);
                })
                .orElseThrow(
                        () -> new EntityNotFoundException("Task hasn't been found")
                );
    }

    @Override
    public void assignAssignee(Long id, String assignee) {
        User newAssignee = userRepository.findByUsername(assignee);
        if (newAssignee != null) {
            repository.findById(id)
                    .map(oldTask -> {
                        oldTask.setAssignee(newAssignee);
                        mail.sendEmailToAssignee(newAssignee.getEmail(), oldTask.getName());
                        return repository.save(oldTask);
                    })
                    .orElseThrow(
                            () -> new EntityNotFoundException("Task hasn't been found")
                    );
        }
    }


    @Override
    public void assignReporter(Long id, String reporter) {
        User newReporter = userRepository.findByUsername(reporter);
        if (newReporter != null) {
            repository.findById(id)
                    .map(oldTask -> {
                        oldTask.setUser(newReporter);
                        mail.sendEmailToReporter(newReporter.getEmail(), oldTask.getName());
                        return repository.save(oldTask);
                    })
                    .orElseThrow(
                            () -> new EntityNotFoundException("Task hasn't been found")
                    );
        }
    }

    @Override
    public void setStatus(Long id, Long statusId) {
        Status newStatus = statusRepository.getOne(statusId);
        repository.findById(id)
                .map(oldTask -> {
                    oldTask.setStatus(newStatus);
                    return repository.save(oldTask);
                })
                .orElseThrow(
                        () -> new EntityNotFoundException("Task hasn't been found")
                );
    }
}
