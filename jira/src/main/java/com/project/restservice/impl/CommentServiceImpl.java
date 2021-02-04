package com.project.restservice.impl;

import com.project.EntityNotFoundException;
import com.project.entity.Comment;
import com.project.repository.CommentRepository;
import com.project.repository.TaskRepository;
import com.project.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository repository, UserRepository userRepository, TaskRepository taskRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public void addComment(Comment comment, String username, String task) {

        Comment newComment = new Comment();

        newComment.setText(comment.getText());
        newComment.setDate(comment.getDate());
        newComment.setTask(taskRepository.findByName(task));
        newComment.setUser(userRepository.findByUsername(username));

        repository.save(newComment);
    }

    @Override
    public List<Comment> getComments() {
        return repository.findAll();
    }

    @Override
    public List<Comment> getCommentsByUsername(String username) {
        return repository.getCommentsByUserUsername(username);
    }

    @Override
    public List<Comment> getCommentsByTask(String task) {
        return repository.getCommentsByTaskName(task);
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

    @Override
    public void deleteComment(Long id) {

        repository.deleteById(id);
    }

}
