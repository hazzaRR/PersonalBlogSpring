package com.hazr.personalblog.repository;

import com.hazr.personalblog.model.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {
}
