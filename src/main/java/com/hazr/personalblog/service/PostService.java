package com.hazr.personalblog.service;


import com.hazr.personalblog.dto.FetchedPostDTO;
import com.hazr.personalblog.dto.PostDTO;
import com.hazr.personalblog.model.Post;
import com.hazr.personalblog.model.User;
import com.hazr.personalblog.repository.CategoryRepository;
import com.hazr.personalblog.repository.PostRepository;
import com.hazr.personalblog.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    private final UserRepository userRepository;

    private final AzureBlobService azureBlobService;

    public PostService(PostRepository postRepository, CategoryRepository categoryRepository, UserRepository userRepository, AzureBlobService azureBlobService) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.azureBlobService = azureBlobService;
    }

    public List<FetchedPostDTO> getPosts() {
        List<Post> allPosts = postRepository.findAll();


        List<FetchedPostDTO> posts = allPosts.stream().map(post -> {

            byte[] banner_image = azureBlobService.getFile(post.getBannerImage().getPhotoURL());

            return new FetchedPostDTO(post.getPostId(), post.getTitle(), post.getAuthor().getFirstname(), post.getAuthor().getSurname(),
                    post.getCategories(), post.getPostBody(), post.isPrivatePost(), post.getDate() ,post.getBannerImage().getAltText(), azureBlobService.getFile(post.getBannerImage().getPhotoURL()));

        }).collect(Collectors.toList());

        return posts;
    }

    public Optional<Post> getPostById(long id) {
        return postRepository.findById(id);
    }

    public List<Post> getPostsByCategory(Long categoryId) {
        return postRepository.findByCategory(categoryId);
    }

    public Post createPost(PostDTO post) {

        try {

            User author = userRepository.findByUsername(post.getAuthor()).get();

            Post newPost = new Post(post.getTitle(), author, post.getCategories(), LocalDate.now(), post.getContent(), post.isPrivatePost());

            postRepository.save(newPost);

            return newPost;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public List<FetchedPostDTO> getLatestPosts() {
        List<Post> latestPostsResponse =  postRepository.findLatestPosts();

        List<FetchedPostDTO> latestPosts = latestPostsResponse.stream().map(post -> {

            if (post.getBannerImage() != null) {

                return new FetchedPostDTO(post.getPostId(), post.getTitle(), post.getAuthor().getFirstname(), post.getAuthor().getSurname(),
                        post.getCategories(), post.getPostBody(), post.isPrivatePost(), post.getDate(), post.getBannerImage().getAltText(), azureBlobService.getFile(post.getBannerImage().getPhotoURL()));

            }
            else {

                return new FetchedPostDTO(post.getPostId(), post.getTitle(), post.getAuthor().getFirstname(), post.getAuthor().getSurname(),
                        post.getCategories(), post.getPostBody(), post.isPrivatePost(), post.getDate());


            }

        }).toList();

        return latestPosts;
    }

    public List<FetchedPostDTO> getPublicPosts() {
        List<Post> latestPostsResponse =  postRepository.findPublicPosts();

        List<FetchedPostDTO> latestPosts = latestPostsResponse.stream().map(post -> {

            if (post.getBannerImage() != null) {

                return new FetchedPostDTO(post.getPostId(), post.getTitle(), post.getAuthor().getFirstname(), post.getAuthor().getSurname(),
                        post.getCategories(), post.getPostBody(), post.isPrivatePost(), post.getDate(), post.getBannerImage().getAltText(), azureBlobService.getFile(post.getBannerImage().getPhotoURL()));

            }
            else {

                return new FetchedPostDTO(post.getPostId(), post.getTitle(), post.getAuthor().getFirstname(), post.getAuthor().getSurname(),
                        post.getCategories(), post.getPostBody(), post.isPrivatePost(), post.getDate());


            }

        }).collect(Collectors.toList());

        return latestPosts;
    }

    public void deletePost(long id) {
        postRepository.deleteById(id);
    }


    @Transactional
    public void updatePost(Post updatedPost) {

        Post existingPost = postRepository.findById(updatedPost.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found with ID: " + updatedPost.getPostId()));

        existingPost.setTitle(updatedPost.getTitle());
        existingPost.setCategories(updatedPost.getCategories());
        existingPost.setDate(updatedPost.getDate());
        existingPost.setPostBody(updatedPost.getPostBody());
        existingPost.setPrivatePost(updatedPost.isPrivatePost());

    }
}
