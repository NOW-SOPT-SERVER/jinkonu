package org.sopt.common.dto.request;

import org.sopt.domain.Member;
import org.sopt.domain.Part;

public record MemberJoinRequest(
        String name,
        Part part,
        int age
) {

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .part(part)
                .age(age)
                .build();
    }
}
