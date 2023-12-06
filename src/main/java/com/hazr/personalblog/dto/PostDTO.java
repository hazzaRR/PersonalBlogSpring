package com.hazr.personalblog.dto;

import java.util.Set;

public class PostDTO {

    private String title;

    private String author;

    private Set<Long> categories;

    private String content;

    private boolean privatePost;


    public PostDTO() {
    }

    public PostDTO(String title, String author, Set<Long> categories, String content, boolean privatePost) {
        this.title = title;
        this.author = author;
        this.categories = categories;
        this.content = content;
        this.privatePost = privatePost;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public Set<Long> getCategories() {
        return categories;
    }

    public void setCategories(Set<Long> categories) {
        this.categories = categories;
    }


    @Override
    public String toString() {
        return "PostDTO{" +
                "title='" + title + '\'' +
                ", username='" + author + '\'' +
                ", categoryIds=" + categories +
                ", content='" + content + '\'' +
                ", privatePost=" + privatePost +
                '}';
    }
}
