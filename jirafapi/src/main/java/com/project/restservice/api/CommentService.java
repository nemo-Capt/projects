package com.project.restservice.api;

import com.project.entity.Comment;

import java.util.List;

public interface CommentService {

    Comment addComment(Comment comment, String username, String task);

    List<Comment> getComments();

    List<Comment> getCommentsByUsername(String username);

    List<Comment> getCommentsByTask(String task);

    void deleteComment(Long id);
}
