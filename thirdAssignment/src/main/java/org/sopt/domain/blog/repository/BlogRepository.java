package org.sopt.domain.blog.repository;

import org.sopt.domain.blog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    Optional<Blog> findByMemberId(Long memberId);
}
