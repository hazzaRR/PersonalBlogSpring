package com.hazr.personalblog.controller;

import com.hazr.personalblog.dto.FetchedPostDTO;
import com.hazr.personalblog.dto.PostDTO;
import com.hazr.personalblog.dto.UpdatePostDTO;
import com.hazr.personalblog.exception.PostDoesNotExistException;
import com.hazr.personalblog.exception.UsernameAlreadyTakenException;
import com.hazr.personalblog.exception.UsernameDoesNotExistException;
import com.hazr.personalblog.model.Post;
import com.hazr.personalblog.service.AzureBlobService;
import com.hazr.personalblog.service.PostImageService;
import com.hazr.personalblog.service.PostService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = {"http://localhost:5173", "https://blog.harryredman.com/"})
public class PostController {


    private final PostService postService;

    private final AzureBlobService azureBlobService;

    private final PostImageService postImageService;

    private final JwtDecoder jwtDecoder;

    public PostController(PostService postService, AzureBlobService azureBlobService, PostImageService postImageService, JwtDecoder jwtDecoder) {
        this.postService = postService;
        this.azureBlobService = azureBlobService;
        this.postImageService = postImageService;
        this.jwtDecoder = jwtDecoder;
    }


//    @GetMapping("/test")
//    public String testEndpoint(@RequestHeader (name="Authorization") String token) {
//
//        String[] tokenValue = token.trim().split(" ");
//
//        System.out.println(tokenValue[1]);
//
//        Jwt decodedToken =  jwtDecoder.decode(tokenValue[1]);
//
//        System.out.println(decodedToken.getSubject());
//
//        return "This is a test";
//    }


    //get all posts
    @GetMapping("/")
    public List<FetchedPostDTO> getPosts() {
        System.out.println("we get here");
        return postService.getPosts();
    }

    //get specific post by id
    @GetMapping(path = "/postId/{id}")
    public FetchedPostDTO getPostById(@PathVariable long id) {
        return postService.getPostById(id);
    }

    //get post by title


    //get all posts with title that contains given string

    //get posts by category

    //get posts by certain author
    @PreAuthorize("hasAnyRole(\"AUTHOR\", \"ADMIN\")")
    @GetMapping ("/user-posts/{username}")
    public List<FetchedPostDTO> getPostsByAuthor(@PathVariable String username) {
        return postService.getPostsByAuthor(username);
    }

    //get all public posts

    @GetMapping ("/public")
    public List<FetchedPostDTO> getPublicPosts() {
        return postService.getPublicPosts();
    }

    //get top 10 latest posts

    @GetMapping("/latest")
    public List<FetchedPostDTO> getLatestPosts() {
        return postService.getLatestPosts();
    }


    // add a post

    @PostMapping("/")
    public String createPost(@RequestPart("postDetails") PostDTO postDetails,
                             @RequestPart(value = "bannerImage", required = false) MultipartFile image_file) {
        try {

            Post post = postService.createPost(postDetails);


            if (image_file != null) {
                System.out.println(image_file);

                String fileName = azureBlobService.upload("postId_" + post.getPostId() + "_bannerImage", image_file);

                System.out.println(fileName);
                postImageService.createPostImage(post, fileName, postDetails.getAltText());

            }


            return "Post uploaded successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error uploading post";
        }
    }

    //delete post

    @DeleteMapping("/postId/{id}")
    public void deletePostById(@PathVariable long id) {
        postService.deletePost(id);
    }

    //update post
    @PutMapping("/postId/{id}")
    public void updatePost(@PathVariable long id, @RequestPart("postDetails") UpdatePostDTO postDetails, @RequestPart(value = "bannerImage", required = false) MultipartFile image_file) {

        try {

            postService.updatePost(id, postDetails, image_file);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @ExceptionHandler(PostDoesNotExistException.class)
    public ResponseEntity<String> handlePostDoesNotExistException(PostDoesNotExistException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(UsernameDoesNotExistException.class)
    public ResponseEntity<String> handleUsernameDoesNotExistException(UsernameDoesNotExistException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
