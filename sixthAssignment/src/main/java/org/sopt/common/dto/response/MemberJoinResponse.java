package org.sopt.common.dto.response;

import org.sopt.common.auth.dto.AuthToken;

public record MemberJoinResponse(
        String accessToken,
        String refreshToken,
        String userId
) {

    public static MemberJoinResponse of(
            AuthToken token,
            String userId
    ) {
        return new MemberJoinResponse(token.accessToken(), token.refreshToken(), userId);
    }
}
