package org.sopt.domain.blog.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.sopt.domain.blog.entity.Blog;
import org.sopt.domain.member.entity.Member;

public record BlogCreateRequest(
        @NotNull(message = "블로그를 개설할 사용자가 누락되었습니다.") Long memberId,
        @Size(max = 20, message = "블로그 제목이 최대 글자 수(20자)를 초과했습니다.") String title,
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
