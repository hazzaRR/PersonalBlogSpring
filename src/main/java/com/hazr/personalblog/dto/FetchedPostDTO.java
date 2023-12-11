package com.hazr.personalblog.dto;

import com.hazr.personalblog.model.Category;

import java.time.LocalDate;
import java.util.List;

public class FetchedPostDTO {

    private Long postId;

    private String title;

    private String firstname;

    private String surname;

    private List<Category> categories;

    private String content;

    private boolean privatePost;

    private LocalDate postedOn;

    private String altText;

    private byte[] bannerImage;
    private byte[] authorImage;


    public FetchedPostDTO() {
    }

    public FetchedPostDTO(Long postId, String title, String firstname, String surname, List<Category> categories, String content, boolean privatePost, LocalDate postedOn, String altText, byte[] bannerImage) {
        this.postId = postId;
        this.title = title;
        this.firstname = firstname;
        this.surname = surname;
        this.categories = categories;
        this.content = content;
        this.privatePost = privatePost;
        this.postedOn = postedOn;
        this.altText = altText;
        this.bannerImage = bannerImage;
    }

    public FetchedPostDTO(Long postId, String title, String firstname, String surname, List<Category> categories, String content, boolean privatePost, LocalDate postedOn) {
        this.postId = postId;
        this.title = title;
        this.firstname = firstname;
        this.surname = surname;
        this.categories = categories;
        this.content = content;
        this.privatePost = privatePost;
        this.postedOn = postedOn;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public byte[] getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(byte[] bannerImage) {
        this.bannerImage = bannerImage;
    }

    public byte[] getAuthorImage() {
        return authorImage;
    }

    public void setAuthorImage(byte[] authorImage) {
        this.authorImage = authorImage;
    }

    public LocalDate getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(LocalDate postedOn) {
        this.postedOn = postedOn;
    }
}
