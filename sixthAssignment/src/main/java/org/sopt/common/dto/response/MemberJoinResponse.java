package org.sopt.common.dto.response;

public record MemberJoinResponse(
        String accessToken,
        String userId
) {

    public static MemberJoinResponse of(
            String accessToken,
            String userId
    ) {
        return new MemberJoinResponse(accessToken, userId);
    }
}
