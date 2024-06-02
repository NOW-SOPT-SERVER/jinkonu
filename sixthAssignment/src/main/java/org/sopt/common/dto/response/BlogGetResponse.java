package org.sopt.common.dto.response;

import org.sopt.domain.Blog;

import java.time.LocalDateTime;

public record BlogGetResponse(
        String title,
        String description,
        String memberName,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static BlogGetResponse of(Blog blog) {
        return new BlogGetResponse(
                blog.getTitle(),
                blog.getDescription(),
                blog.getMember().getName(),
                blog.getCreatedAt(),
                blog.getUpdatedAt()
        );
    }
}
