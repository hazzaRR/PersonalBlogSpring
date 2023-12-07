package com.hazr.personalblog.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    private Long postId;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToMany
    @JoinTable(
            name = "blogpost_category",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    public PostImage getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(PostImage bannerImage) {
        this.bannerImage = bannerImage;
    }

    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PostImage bannerImage;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "post_body")
    private String postBody;

    @Column(name = "private_post")
    private boolean privatePost;

    protected Post() {
    }

    public Post(Long postId, String title, User author, List<Category> categories, LocalDate date, String postBody, boolean privatePost) {
        this.postId = postId;
        this.title = title;
        this.author = author;
        this.categories = categories;
        this.date = date;
        this.postBody = postBody;
        this.privatePost = privatePost;
    }

    public Post(String title, User author, List<Category> categories, LocalDate date, String postBody, boolean privatePost) {
        this.title = title;
        this.author = author;
        this.categories = categories;
        this.date = date;
        this.postBody = postBody;
        this.privatePost = privatePost;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
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
}


