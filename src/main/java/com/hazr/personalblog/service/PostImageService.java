package com.hazr.personalblog.service;

import com.hazr.personalblog.repository.PostImageRepository;
import org.springframework.stereotype.Service;

@Service
public class PostImageService {

    private final PostImageRepository postImageRepository;

    public PostImageService(PostImageRepository postImageRepository) {
        this.postImageRepository = postImageRepository;
    }
}
