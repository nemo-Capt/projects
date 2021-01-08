package com.project.controller;

import com.project.entity.Comment;
import com.project.restservice.api.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/comments")
public class CommentController {

    private final CommentService service;

    @Autowired
    public CommentController(CommentService service) {
        this.service = service;
    }


    @GetMapping
    public List<Comment> findAll() {

        List<Comment> roles = service.getComments();
        return roles;
    }

    @GetMapping(path = "/{id}")
    public Optional<Comment> find(@PathVariable("id") Long id) {

        Optional<Comment> role = service.getComment(id);
        return role;
    }

    @PostMapping(consumes = "application/json")
    public void create(@RequestBody Comment comment) {
        service.addComment(comment);
    }

    @PutMapping(path = "/{id}")
    public void edit(@RequestBody Comment comment, @PathVariable long id) {
        service.editComment(comment, id);
    }

}
