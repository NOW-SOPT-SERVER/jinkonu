package org.sopt.common.dto.response;

public record MemberTokenRefreshResponse(
        String accessToken
) {

    public static MemberTokenRefreshResponse of(String accessToken) {
        return new MemberTokenRefreshResponse(accessToken);
    }
}
