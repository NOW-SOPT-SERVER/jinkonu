package org.sopt.domain.post.repository;

import org.sopt.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByBlogId(Long blogId);
}
