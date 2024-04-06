package org.sopt.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.sopt.domain.Member;
import org.sopt.repository.MemberRepository;
import org.sopt.service.dto.MemberCreateDto;
import org.sopt.service.dto.MemberFindDto;
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

    public MemberFindDto findMemberById(Long id) {
        return MemberFindDto.of(memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ID에 해당되는 사용자가 없습니다"))
        );
    }

    public void deleteMemberById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ID에 해당되는 사용자가 없습니다"));

        memberRepository.delete(member);
    }
}
