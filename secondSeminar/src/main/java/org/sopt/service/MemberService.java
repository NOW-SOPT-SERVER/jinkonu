package org.sopt.service;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.Member;
import org.sopt.repository.MemberRepository;
import org.sopt.service.dto.MemberCreateDto;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public String createMember(
            MemberCreateDto dto
    ) {
        Member member = memberRepository.save(
                Member.create(dto.name(), dto.part(), dto.age())
        );
        return member.getId().toString();
    }
}
