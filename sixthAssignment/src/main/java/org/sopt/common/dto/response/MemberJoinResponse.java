package org.sopt.common.dto.response;

import org.sopt.common.auth.dto.AuthTokenSet;

public record MemberJoinResponse(
        String accessToken,
        String refreshToken,
        String userId
) {

    public static MemberJoinResponse of(
            AuthTokenSet token,
            String userId
    ) {
        return new MemberJoinResponse(token.accessToken(), token.refreshToken(), userId);
    }
}
