package org.sopt.domain.member.dto.response;

import org.sopt.domain.member.entity.Member;
import org.sopt.domain.member.entity.Part;

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
