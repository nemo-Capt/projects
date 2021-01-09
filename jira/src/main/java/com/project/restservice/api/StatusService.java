package com.project.restservice.api;

import com.project.entity.Status;

import java.util.List;
import java.util.Optional;

public interface StatusService {

    void addStatus(Status status);

    List<Status> getStatuses();

    Optional<Status> getStatus(Long id);
}
