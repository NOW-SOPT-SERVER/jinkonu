package org.sopt.common.dto.response;

import org.sopt.domain.Blog;

import java.util.List;

public record BlogGetAllResponse(
        int count,
        List<BlogGetResponse> blogs
) {
    public static BlogGetAllResponse of(List<Blog> blogs) {
        return new BlogGetAllResponse(
                blogs.size(),
                blogs.stream()
                        .map(BlogGetResponse::of)
                        .toList()
        );
    }
}
