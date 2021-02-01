package com.project.restservice.impl;

import com.project.config.Constants;
import com.project.entity.Comment;
import com.project.entity.Project;
import com.project.restservice.api.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private final RestTemplate restTemplate;

    @Autowired
    public CommentServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public List<Comment> getComments() {
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(Constants.COMMENT_URL, Comment[].class)));
    }

    @Override
    public List<Comment> getCommentsByUsername(String username) {
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(Constants.COMMENT_URL + "/username/" + username, Comment[].class)));
    }
}
