package com.hazr.personalblog.service;


import com.hazr.personalblog.dto.FetchedPostDTO;
import com.hazr.personalblog.dto.PostDTO;
import com.hazr.personalblog.dto.UpdatePostDTO;
import com.hazr.personalblog.exception.PostDoesNotExistException;
import com.hazr.personalblog.exception.UsernameDoesNotExistException;
import com.hazr.personalblog.model.Post;
import com.hazr.personalblog.model.User;
import com.hazr.personalblog.repository.CategoryRepository;
import com.hazr.personalblog.repository.PostRepository;
import com.hazr.personalblog.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    private final PostImageService postImageService;

    private final UserRepository userRepository;

    private final AzureBlobService azureBlobService;

    public PostService(PostRepository postRepository, CategoryRepository categoryRepository, PostImageService postImageService, UserRepository userRepository, AzureBlobService azureBlobService) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
        this.postImageService = postImageService;
        this.userRepository = userRepository;
        this.azureBlobService = azureBlobService;

    }

    private FetchedPostDTO getFetchedPostDTO(Post postDetails) {
        FetchedPostDTO fetchedPost;

        if (postDetails.getBannerImage() != null) {

            fetchedPost =  new FetchedPostDTO(postDetails.getPostId(), postDetails.getTitle(), postDetails.getAuthor().getFirstname(), postDetails.getAuthor().getSurname(),
                    postDetails.getCategories(), postDetails.getPostBody(), postDetails.isPrivatePost(), postDetails.getDate(), postDetails.getBannerImage().getAltText(), azureBlobService.getFile(postDetails.getBannerImage().getPhotoURL()));

        }
        else {

        fetchedPost =  new FetchedPostDTO(postDetails.getPostId(), postDetails.getTitle(), postDetails.getAuthor().getFirstname(), postDetails.getAuthor().getSurname(),
                postDetails.getCategories(), postDetails.getPostBody(), postDetails.isPrivatePost(), postDetails.getDate());
        }

        if (postDetails.getAuthor().getProfilePicURL() != null) {
        fetchedPost.setAuthorImage(azureBlobService.getFile(postDetails.getAuthor().getProfilePicURL()));
        }


        return fetchedPost;
    }
    public List<FetchedPostDTO> convertPostListToFetchedPostDTOList(List<Post> posts) {

        return posts.stream().map(post -> {

            return getFetchedPostDTO(post);

        }).toList();

    }

    public FetchedPostDTO getPostById(long id) throws PostDoesNotExistException {
        Optional<Post> post = postRepository.findById(id);

        if (post.isEmpty()) {
            throw new PostDoesNotExistException("the post with the id"+ id + " does not exist");
        }

        Post postDetails = post.get();

        return getFetchedPostDTO(postDetails);
    }

    public List<FetchedPostDTO> getPosts() {
        List<Post> allPosts = postRepository.findAll();
        return convertPostListToFetchedPostDTOList(allPosts);
    }

    public List<FetchedPostDTO> getPostsByCategory(Long categoryId) {
        List<Post> categoryPostsResponse = postRepository.findByCategory(categoryId);

        return convertPostListToFetchedPostDTOList(categoryPostsResponse);
    }

    public List<FetchedPostDTO> getLatestPosts() {
        List<Post> latestPostsResponse =  postRepository.findLatestPosts();

        return convertPostListToFetchedPostDTOList(latestPostsResponse);
    }

    public List<FetchedPostDTO> getPublicPosts() {
        List<Post> publicPostsResponse =  postRepository.findPublicPosts();

        return convertPostListToFetchedPostDTOList(publicPostsResponse);

    }

    public List<FetchedPostDTO> getPostsByAuthor(String username) {

        Optional<User> userAccount = userRepository.findByUsername(username);


        if (userAccount.isEmpty()) {
            throw new UsernameDoesNotExistException("the username "+ username + " does not exist");
        }
        List<Post> authorPostsResponse = postRepository.findByAuthor(userAccount.get());

        return convertPostListToFetchedPostDTOList(authorPostsResponse);


    }
    public Post createPost(PostDTO post) {

        try {

            Optional<User> user = userRepository.findByUsername(post.getAuthor());

            if (user.isEmpty()) {
                throw new UsernameDoesNotExistException("the username "+ post.getAuthor() + " does not exist");
            }

            User author = user.get();
            Post newPost = new Post(post.getTitle(), author, post.getCategories(), LocalDate.now(), post.getContent(), post.isPrivatePost());

            postRepository.save(newPost);

            return newPost;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public void deletePost(long id) {

        Optional<Post> post = postRepository.findById(id);

        if (post.isEmpty()) {
            throw new PostDoesNotExistException("Post not found with ID: " + id);
        }

        if(post.get().getBannerImage() != null && post.get().getBannerImage().getPhotoURL() != null) {
            azureBlobService.deleteBlob(post.get().getBannerImage().getPhotoURL());
        }

        postRepository.deleteById(id);
    }

    @Transactional
    public void updatePost(long id, UpdatePostDTO updatedPost, MultipartFile image_file) throws IOException {

        Post existingPost = postRepository.findById(id)
                .orElseThrow(() -> new PostDoesNotExistException("Post not found with ID: " + id));


        if (image_file != null) {
            String fileName = azureBlobService.upload("postId_" + existingPost.getPostId() + "_bannerImage", image_file);
            if (existingPost.getBannerImage() == null) {
                postImageService.createPostImage(existingPost, fileName, updatedPost.getAltText());
            }

        }

        if(updatedPost.getTitle() != null && !updatedPost.getTitle().isEmpty() && !Objects.equals(updatedPost.getTitle(), existingPost.getTitle())) {
            existingPost.setTitle(updatedPost.getTitle());
        }

        if(updatedPost.getCategories() != null && !updatedPost.getCategories().isEmpty() && !Objects.equals(updatedPost.getCategories(), existingPost.getCategories())) {
            existingPost.setCategories(updatedPost.getCategories());
        }

        if(updatedPost.getContent() != null && !updatedPost.getContent().isEmpty() && !Objects.equals(updatedPost.getContent(), existingPost.getPostBody())) {
            existingPost.setPostBody(updatedPost.getContent());
        }

        if(updatedPost.getAltText() != null && !updatedPost.getAltText().isEmpty() && !Objects.equals(updatedPost.getAltText(), existingPost.getBannerImage().getAltText())) {
            existingPost.getBannerImage().setAltText(updatedPost.getAltText());
        }

        if(!Objects.equals(updatedPost.isPrivatePost(), existingPost.isPrivatePost())) {
            existingPost.setPrivatePost(updatedPost.isPrivatePost());
        }

    }

}
