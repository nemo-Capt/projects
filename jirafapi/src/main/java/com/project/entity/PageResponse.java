package com.project.entity;

import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.List;

public class PageResponse<T> {

    @JsonProperty("content")
    private List<T> content;
    @JsonProperty("totalElements")
    private int totalPages;
    @JsonProperty("totalPages")
    private long totalElements;

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
}
