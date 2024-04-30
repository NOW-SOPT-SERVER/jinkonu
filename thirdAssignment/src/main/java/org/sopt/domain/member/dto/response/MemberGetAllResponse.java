package org.sopt.domain.member.dto.response;

import java.util.List;

public record MemberGetAllResponse(
        Long count,
        List<MemberGetResponse> memberDtos
) {
    public static MemberGetAllResponse of(
            long count,
            List<MemberGetResponse> members
    ) {
        return new MemberGetAllResponse(count, members);
    }
}
