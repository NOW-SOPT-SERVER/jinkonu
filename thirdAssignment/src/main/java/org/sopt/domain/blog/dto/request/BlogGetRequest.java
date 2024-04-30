package org.sopt.domain.blog.dto.request;

import jakarta.validation.constraints.NotNull;

public record BlogGetRequest(
        @NotNull(message = "블로그의 사용자가 누락되었습니다.") Long memberId
) {
}
