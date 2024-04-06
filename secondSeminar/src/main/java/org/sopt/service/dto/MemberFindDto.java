package org.sopt.service.dto;

import org.sopt.domain.Member;

public record MemberFindDto(
        String name,
        Member.Part part,
        int age
) {

    public static MemberFindDto of(Member member) {
        return new MemberFindDto(member.getName(), member.getPart(), member.getAge());
    }
}
