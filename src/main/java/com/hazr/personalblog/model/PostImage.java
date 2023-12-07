package com.hazr.personalblog.model;


import jakarta.persistence.*;

@Entity
@Table(name = "post_image")
public class PostImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_image_id")
    private Long postImageId;

    @OneToOne
    @JoinColumn(name = "Post_id", nullable = false)
    private Post post;

    @Column(name = "photo_url")
    private String photoURL;

    @Column(name = "alt_text")
    private String altText;

    protected PostImage() {
    }

    public PostImage(Long postImageId, Post post, String photoURL, String altText) {
        this.postImageId = postImageId;
        this.post = post;
        this.photoURL = photoURL;
        this.altText = altText;
    }

    public PostImage(Post post, String photoURL, String altText) {
        this.post = post;
        this.photoURL = photoURL;
        this.altText = altText;
    }

    public Long getPostImageId() {
        return postImageId;
    }

    public void setPostImageId(Long postImageId) {
        this.postImageId = postImageId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getAltText() {
        return altText;
    }

    public void setAltText(String metaData) {
        this.altText = metaData;
    }
}
