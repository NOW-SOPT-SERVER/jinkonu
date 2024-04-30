package org.sopt.domain.post.dto.request;

import org.sopt.domain.blog.entity.Blog;
import org.sopt.domain.post.entity.Post;

public record PostCreateRequest(
        Long memberId,
        String token,
        String title,
        String content
) {
    public Post toEntity(Blog blog) {
        return Post.builder()
                .title(title)
                .content(content)
                .blog(blog)
                .build();
    }
}
