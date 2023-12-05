package com.hazr.personalblog.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class PostDTO {

    private String title;

    private String username;

    private Set<Long> categoryIds;

    private LocalDate date;

    private String content;

    private boolean privatePost;

    private List<ImageDTO> images;

    public PostDTO() {
    }

    public PostDTO(String title, String username, Set<Long> categoryIds, LocalDate date, String content, boolean privatePost, List<ImageDTO> images) {
        this.title = title;
        this.username = username;
        this.categoryIds = categoryIds;
        this.date = date;
        this.content = content;
        this.privatePost = privatePost;
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public List<ImageDTO> getImages() {
        return images;
    }

    public void setImages(List<ImageDTO> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "title='" + title + '\'' +
                ", username='" + username + '\'' +
                ", categoryIds=" + categoryIds +
                ", date=" + date +
                ", content='" + content + '\'' +
                ", privatePost=" + privatePost +
                ", images=" + images +
                '}';
    }
}
