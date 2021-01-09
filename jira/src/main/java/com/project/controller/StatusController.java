package com.project.controller;


import com.project.entity.Status;
import com.project.restservice.api.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/statuses")
public class StatusController {

    private final StatusService service;

    @Autowired
    StatusController(StatusService service) {
        this.service = service;
    }

    @PostMapping(consumes = "application/json")
    public void create(@RequestBody Status status) {
        service.addStatus(status);
    }

    @GetMapping
    public List<Status> findAll() {

        List<Status> statuses = service.getStatuses();
        return statuses;
    }

    @GetMapping(path = "/{id}")
    public Optional<Status> find(@PathVariable("id") Long id) {

        Optional<Status> status = service.getStatus(id);
        return status;
    }
}
