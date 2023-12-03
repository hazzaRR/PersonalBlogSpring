package com.hazr.personalblog.controller;

import com.hazr.personalblog.dto.PostDTO;
import com.hazr.personalblog.model.Post;
import com.hazr.personalblog.service.PostService;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts/")
public class PostController {


    private final PostService postService;

    private final JwtDecoder jwtDecoder;

    public PostController(PostService postService, JwtDecoder jwtDecoder) {
        this.postService = postService;
        this.jwtDecoder = jwtDecoder;
    }

    @GetMapping("/test")
    public String testEndpoint(@RequestHeader (name="Authorization") String token) {

        String[] tokenValue = token.trim().split(" ");

        System.out.println(tokenValue[1]);

        Jwt decodedToken =  jwtDecoder.decode(tokenValue[1]);

        System.out.println(decodedToken.getSubject());

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

    @GetMapping ("/public")

    public List<Post> getPublicPosts() {
        return postService.getPublicPosts();
    }

    //get top 8 latest posts

    @GetMapping("/latest")
    public List<Post> getLatestPosts() {
        return postService.getLatestPosts();
    }


    // add a post

    @PostMapping("/")
    public void createPost(@RequestBody PostDTO post) {
        postService.createPost(post);
    }

    //delete post

    //update post
}
