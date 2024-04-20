package org.sopt.common.dto.response;

import org.sopt.domain.Member;
import org.sopt.domain.Part;

public record MemberGetResponse(
        String name,
        Part part,
        int age
) {

    public static MemberGetResponse of(
            Member member
    ) {
        return new MemberGetResponse(
                member.getName(),
                member.getPart(),
                member.getAge()
        );
    }
}
