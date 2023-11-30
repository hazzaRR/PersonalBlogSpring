package com.hazr.personalblog.controller;

import com.hazr.personalblog.model.Post;
import com.hazr.personalblog.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts/")

public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/test")
    public String testEndpoint() {
        return "This is a test";
    }

    @GetMapping("/all")
    public List<Post> getPosts() {
        return postService.getPosts();
    }
}
