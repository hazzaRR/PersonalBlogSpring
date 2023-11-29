package com.hazr.personalblog.model;


import jakarta.persistence.*;

@Entity
@Table(name = "post_image")
public class PostImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_image_id")
    private Long postImageId;

    @ManyToOne
    @JoinColumn(name = "Post_id", nullable = false)
    private Post post;

    @Column(name = "photo_url")
    private String photoURL;

    @Column(name = "metadata")
    private String metaData;

    protected PostImage() {
    }

    public PostImage(Long postImageId, Post post, String photoURL, String metaData) {
        this.postImageId = postImageId;
        this.post = post;
        this.photoURL = photoURL;
        this.metaData = metaData;
    }

    public PostImage(Post post, String photoURL, String metaData) {
        this.post = post;
        this.photoURL = photoURL;
        this.metaData = metaData;
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

    public String getMetaData() {
        return metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }
}
