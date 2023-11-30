package com.hazr.personalblog.repository;

import com.hazr.personalblog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
