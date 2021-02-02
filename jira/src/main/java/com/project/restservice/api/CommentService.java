package com.project.restservice.api;

import com.project.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    void addComment(Comment comment, String username, String task);

    List<Comment> getComments();

    Optional<Comment> getComment(Long id);

    void editComment(Comment comment, Long id);

    List<Comment> getCommentsByUsername(String username);

    void deleteComment(Long id);

}
