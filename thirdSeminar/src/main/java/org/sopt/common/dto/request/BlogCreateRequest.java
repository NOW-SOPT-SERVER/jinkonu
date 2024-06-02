package org.sopt.common.dto.request;

import org.sopt.domain.Blog;
import org.sopt.domain.Member;

public record BlogCreateRequest(
        String title,
        String description
) {
    public Blog toEntity(Member member) {
        return Blog.builder()
                .member(member)
                .title(title)
                .description(description)
                .build();
    }
}
