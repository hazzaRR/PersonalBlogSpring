package com.hazr.personalblog.service;


import com.hazr.personalblog.dto.PostDTO;
import com.hazr.personalblog.model.Category;
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

@Service
public class PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
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

    public List<Post> getLatestPosts() {
        return postRepository.findLatestPosts();
    }

    public List<Post> getPublicPosts() {
        return postRepository.findPublicPosts();
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
