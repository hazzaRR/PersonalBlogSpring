package com.hazr.personalblog.dto;

import com.hazr.personalblog.model.Category;

import java.util.List;

public class UpdatePostDTO {

    private String title;
    private List<Category> categories;

    private String content;

    private boolean privatePost;

    private String altText;


    public UpdatePostDTO() {
    }

    public UpdatePostDTO(String title, List<Category> categories, String content, boolean privatePost, String altText) {
        this.title = title;
        this.categories = categories;
        this.content = content;
        this.privatePost = privatePost;
        this.altText = altText;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
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

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }
}
