package org.sopt.common.auth.dto;

public record AuthTokenSet(
        String accessToken,
        String refreshToken
) {

    public static AuthTokenSet of(String accessToken, String refreshToken) {
        return new AuthTokenSet(accessToken, refreshToken);
    }

    public enum Type {
        ACCESS_TOKEN,
        REFRESH_TOKEN
    }
}
