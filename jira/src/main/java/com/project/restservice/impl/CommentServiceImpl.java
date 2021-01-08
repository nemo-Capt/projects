package com.project.restservice.impl;

import com.project.EntityNotFoundException;
import com.project.entity.Comment;
import com.project.repository.CommentRepository;
import com.project.restservice.api.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;

    @Autowired
    public CommentServiceImpl(CommentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addComment(Comment comment) {
        repository.save(comment);
    }

    @Override
    public List<Comment> getComments() {
        return repository.findAll();
    }

    @Override
    public Optional<Comment> getComment(Long id) {
        return repository.findById(id);
    }

    @Override
    public void editComment(Comment comment, Long id) {
        repository.findById(id)
                .map(oldComment -> {
                    oldComment.setText(comment.getText());
                    return repository.save(oldComment);
                })
                .orElseThrow(
                        () -> new EntityNotFoundException("Task hasn't been found")
                );
    }
}
