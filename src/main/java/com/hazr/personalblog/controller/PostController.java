package com.hazr.personalblog.controller;

import com.hazr.personalblog.dto.PostDTO;
import com.hazr.personalblog.model.Post;
import com.hazr.personalblog.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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


    //get all posts
    @GetMapping("/")
    public List<Post> getPosts() {
        return postService.getPosts();
    }


    //get specific post by id

    @GetMapping(path = "/{id}")
    public Optional<Post> getPostById(@PathVariable long id) {
        return postService.getPostById(id);
    }

    //get post by title

    //get all posts with title that contains given string

    //get posts by category

//    @GetMapping(name = "/search")
//    public List<Post> getPostByCategory(@RequestParam(required = false) Long categoryId) {
//        return postService.getPostsByCategory(categoryId);
//
//    }

    //get all public posts


    // add a post

    @PostMapping("/")
    public void createPost(@RequestBody PostDTO post) {
        postService.createPost(post);
    }

    //delete post

    //update post
}
