package com.hazr.personalblog.service;

import com.hazr.personalblog.dto.PostDTO;
import com.hazr.personalblog.model.Category;
import com.hazr.personalblog.model.Post;
import com.hazr.personalblog.model.PostImage;
import com.hazr.personalblog.model.User;
import com.hazr.personalblog.repository.PostImageRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PostImageService {

    private final PostImageRepository postImageRepository;

    public PostImageService(PostImageRepository postImageRepository) {
        this.postImageRepository = postImageRepository;
    }

    @Transactional
    public String createPostImage(Post post, String image, String altText) {

        try {

            PostImage newImage = new PostImage(post, image, altText);

            PostImage postImage = postImageRepository.save(newImage);

            post.setBannerImage(postImage);

            return "set banner image for post";
        }
        catch (Exception e) {
            System.out.println(e.getMessage());

            return "error setting banner image for post";
        }

    }
}
