package com.project.restservice.impl;

import com.project.EntityNotFoundException;
import com.project.entity.Assignee;
import com.project.entity.Status;
import com.project.entity.Task;
import com.project.entity.User;
import com.project.repository.AssigneeRepository;
import com.project.repository.StatusRepository;
import com.project.repository.TaskRepository;
import com.project.repository.UserRepository;
import com.project.restservice.api.TaskService;
import com.project.restservice.dto.TaskDTO;
import com.project.restservice.dto.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;
    private final UserRepository userRepository;
    private final AssigneeRepository assigneeRepository;
    private final StatusRepository statusRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository repository, UserRepository userRepository, AssigneeRepository assigneeRepository, StatusRepository statusRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.assigneeRepository = assigneeRepository;
        this.statusRepository = statusRepository;
    }

    @Override
    public Page<TaskDTO> getTasks(Pageable pageable) {
        return repository.findAll(pageable)
                .map(TaskMapper::createDTO);
    }

    @Override
    public void addTask(Task task) {
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
//        Page<TaskDTO> tasks = repository.findAll(pageable).map(TaskMapper::createDTO);
//        List<TaskDTO> newTasks = new ArrayList<>();
//        for (TaskDTO task : tasks) {
//            if (task.getAssignee().equals(assignee)) {
//                newTasks.add(task);
//            }
//        }
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
                    return repository.save(oldTask);
                })
                .orElseThrow(
                        () -> new EntityNotFoundException("Task hasn't been found")
                );
    }

    @Override
    public void assignAssignee(Long id, String assignee) {
        User newAssignee = userRepository.findByUsername(assignee);
        repository.findById(id)
                .map(oldTask -> {
                    oldTask.setAssignee(newAssignee);
                    return repository.save(oldTask);
                })
                .orElseThrow(
                        () -> new EntityNotFoundException("Task hasn't been found")
                );
    }


    @Override
    public void assignReporter(Long id, String reporter) {
        User newReporter = userRepository.findByUsername(reporter);
        repository.findById(id)
                .map(oldTask -> {
                    oldTask.setUser(newReporter);
                    return repository.save(oldTask);
                })
                .orElseThrow(
                        () -> new EntityNotFoundException("Task hasn't been found")
                );
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
