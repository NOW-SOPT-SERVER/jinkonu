package org.sopt.domain.blog.dto.response;

import org.sopt.domain.blog.entity.Blog;

public record BlogGetResponse(
        String title,
        String description,
        String memberName
) {
    public static BlogGetResponse of(Blog blog) {
        return new BlogGetResponse(
                blog.getTitle(),
                blog.getDescription(),
                blog.getMember().getName()
        );
    }
}
