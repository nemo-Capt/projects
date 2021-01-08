package com.project.restservice.impl;

import com.project.EntityNotFoundException;
import com.project.entity.Assignee;
import com.project.entity.Task;
import com.project.entity.User;
import com.project.repository.AssigneeRepository;
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


@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;
    private final UserRepository userRepository;
    private final AssigneeRepository assigneeRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository repository, UserRepository userRepository, AssigneeRepository assigneeRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.assigneeRepository = assigneeRepository;
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
    public void deleteTask(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void editTask(Task task, Long id) {
        repository.findById(id)
                .map(oldTask -> {
                    oldTask.setName(task.getName());
                    oldTask.setUser(task.getUser());
                    oldTask.setAssignee(task.getAssignee());
                    oldTask.setProject(task.getProject());
                    oldTask.setDesc(task.getDesc());
                    oldTask.setPriority(task.getPriority());
                    oldTask.setDuedate(task.getDuedate());
                    oldTask.setEstimatedtime(task.getEstimatedtime());
                    oldTask.setStatus(task.getStatus());
                    return repository.save(oldTask);
                })
                .orElseThrow(
                        () -> new EntityNotFoundException("Task hasn't been found")
                );
    }

    @Override
    public void assignAssignee(Long id, Long assigneeId) {
        Assignee newAssignee = assigneeRepository.getOne(assigneeId);
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
    public void assignReporter(Long id, Long reporterId) {
        User newReporter = userRepository.getOne(reporterId);
        repository.findById(id)
                .map(oldTask -> {
                    oldTask.setUser(newReporter);
                    return repository.save(oldTask);
                })
                .orElseThrow(
                        () -> new EntityNotFoundException("Task hasn't been found")
                );
    }
}
