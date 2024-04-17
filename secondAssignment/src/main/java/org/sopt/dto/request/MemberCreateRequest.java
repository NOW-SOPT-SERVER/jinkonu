package org.sopt.dto.request;

import org.sopt.domain.Member;
import org.sopt.domain.Part;

public record MemberCreateRequest(
        String name,
        Part part,
        int age
) {

    public Member toEntity() {
        return Member.create(name, part, age);
    }
}
