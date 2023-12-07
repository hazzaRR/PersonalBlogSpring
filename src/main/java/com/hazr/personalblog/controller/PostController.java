package com.hazr.personalblog.controller;

import com.hazr.personalblog.dto.FetchedPostDTO;
import com.hazr.personalblog.dto.PostDTO;
import com.hazr.personalblog.model.Post;
import com.hazr.personalblog.service.AzureBlobService;
import com.hazr.personalblog.service.PostImageService;
import com.hazr.personalblog.service.PostService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "http://localhost:5173")
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

    @GetMapping("/hello")
    public String getResponse() {
        return "hello harry";
    }


    //get specific post by id

    @GetMapping(path = "/postId/{id}")
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


//    @GetMapping("/postId/{id}")
//    public ResponseEntity<byte[]> downloadBlob(@PathVariable long id) {
//
//        byte[] data = azureBlobService.getFile(blobName);
//        if (data != null) {
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentLength(data.length);
////            headers.set("Content-Disposition", "attachment; filename=" + blobName);
//            return new ResponseEntity<>(data, headers, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    //get all public posts

    @GetMapping ("/public")
    public List<FetchedPostDTO> getPublicPosts() {
        return postService.getPublicPosts();
    }

    //get top 8 latest posts

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
    @PutMapping("/postId/{postId}")
    public void updatePost(@PathVariable Long postId, @RequestBody Post updatedPost) {
        postService.updatePost(updatedPost);
    }
}
