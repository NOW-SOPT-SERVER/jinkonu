package org.sopt.common.dto.request;

import org.sopt.domain.Blog;
import org.sopt.domain.Member;
import org.springframework.web.multipart.MultipartFile;

public record BlogCreateRequest(
        String title,
        String description,
        MultipartFile image
) {
    public Blog toEntity(Member member, String imageUrl) {
        return Blog.builder()
                .member(member)
                .title(title)
                .description(description)
                .imageUrl(imageUrl)
                .build();
    }
}
