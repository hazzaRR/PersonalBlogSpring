package com.hazr.personalblog.repository;

import com.hazr.personalblog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT p.* FROM post p " +
            "JOIN blogpost_category bc ON p.post_id = bc.post_id " +
            "WHERE bc.category_id = ?1", nativeQuery = true)
    List<Post> findByCategory(long categoryId);


    @Query(value = "SELECT p.* FROM post p " +
            "order by date desc LIMIT 10", nativeQuery = true)
    List<Post> findLatestPosts();

    @Query(value = "SELECT p.* FROM post p " +
            "WHERE p.private_post = false", nativeQuery = true)
    List<Post> findPublicPosts();
}
