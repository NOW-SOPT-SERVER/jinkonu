package org.sopt.domain.member.dto.request;

import org.sopt.domain.member.entity.Member;
import org.sopt.domain.member.entity.Part;

public record MemberCreateRequest(
        String name,
        Part part,
        int age
) {

    public Member toEntity() {
        return Member.create(name, part, age);
    }
}
