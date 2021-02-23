package com.project.repository;

import com.project.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> getCommentsByUserUsername(String username);

    List<Comment> getCommentsByTaskName(String task);

    void deleteAllByTaskName(String taskName);

}
