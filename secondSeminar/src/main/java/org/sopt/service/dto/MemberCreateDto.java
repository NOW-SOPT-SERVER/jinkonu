package org.sopt.service.dto;

import org.sopt.domain.Member;

public record MemberCreateDto(
        String name,
        Member.Part part,
        int age
) {
}
