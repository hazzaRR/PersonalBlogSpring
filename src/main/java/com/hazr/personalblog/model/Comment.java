package com.hazr.personalblog.model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "Post_id", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "comment_body")
    private String commentBody;

    protected Comment() {
    }

    public Comment(Long commentId, Post post, User author, LocalDate date, String commentBody) {
        this.commentId = commentId;
        this.post = post;
        this.author = author;
        this.date = date;
        this.commentBody = commentBody;
    }

    public Comment(Post post, User author, LocalDate date, String commentBody) {
        this.post = post;
        this.author = author;
        this.date = date;
        this.commentBody = commentBody;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }
}
