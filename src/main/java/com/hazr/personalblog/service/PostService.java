package com.hazr.personalblog.service;


import com.hazr.personalblog.dto.PostDTO;
import com.hazr.personalblog.model.Category;
import com.hazr.personalblog.model.Post;
import com.hazr.personalblog.repository.CategoryRepository;
import com.hazr.personalblog.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    public PostService(PostRepository postRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
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

    public void createPost(PostDTO post) {

        try {

//            List<Category> categories = categoryRepository.findAllById(post.getCategoryIds());
//            Post newPost = new Post(post.getTitle(), post.getAuthor_id(), categories, post.getDate(), post.getPostBody(), post.isPrivatePost());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public List<Post> getLatestPosts() {
        return postRepository.findLatestPosts();
    }

    public List<Post> getPublicPosts() {
        return postRepository.findPublicPosts();
    }
}
