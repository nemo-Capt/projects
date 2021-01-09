package com.project.restservice.impl;

import com.project.entity.Status;
import com.project.repository.StatusRepository;
import com.project.restservice.api.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StatusServiceImpl implements StatusService {

    private final StatusRepository repository;

    @Autowired
    StatusServiceImpl(StatusRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addStatus(Status status) {
        repository.save(status);
    }

    @Override
    public List<Status> getStatuses() {
        return repository.findAll();
    }

    @Override
    public Optional<Status> getStatus(Long id) {
        return repository.findById(id);
    }
}
