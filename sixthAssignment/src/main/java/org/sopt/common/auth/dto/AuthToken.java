package org.sopt.common.auth.dto;

public record AuthToken(
        String accessToken,
        String refreshToken
) {
}
