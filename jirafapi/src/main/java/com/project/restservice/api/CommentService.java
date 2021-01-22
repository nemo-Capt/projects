package com.project.restservice.api;

import com.project.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getComments();
}
