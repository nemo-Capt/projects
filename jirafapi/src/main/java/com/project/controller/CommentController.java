package com.project.controller;

import com.project.entity.Comment;
import com.project.restservice.api.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService service;

    @Autowired
    public CommentController(CommentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Comment> getComments() {

        return service.getComments();
    }

    @PostMapping()
    public void addComment(@RequestBody Comment comment, @RequestParam String username, @RequestParam String task) {

        service.addComment(comment, username, task);
    }

    @GetMapping("/username/{username}")
    public List<Comment> getCommentsByUsername(@PathVariable String username) {

        return service.getCommentsByUsername(username);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {

        service.deleteComment(id);
    }

}
