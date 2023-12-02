package com.hazr.personalblog.dto;

import com.hazr.personalblog.model.Category;

import java.time.LocalDate;
import java.util.Set;

public class PostDTO {

    private String title;

    private int author_id;

    private Set<Long> categoryIds;

    private LocalDate date;

    private String postBody;

    private boolean privatePost;

    public PostDTO() {
    }

    public PostDTO(String title, int author_id, Set<Long> categoryIds, LocalDate date, String postBody, boolean privatePost) {
        this.title = title;
        this.author_id = author_id;
        this.categoryIds = categoryIds;
        this.date = date;
        this.postBody = postBody;
        this.privatePost = privatePost;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public boolean isPrivatePost() {
        return privatePost;
    }

    public void setPrivatePost(boolean privatePost) {
        this.privatePost = privatePost;
    }

    public Set<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(Set<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }
}
