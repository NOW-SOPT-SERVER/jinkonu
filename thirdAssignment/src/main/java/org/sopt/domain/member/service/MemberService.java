package org.sopt.domain.member.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.sopt.domain.member.dto.response.MemberCreateResponse;
import org.sopt.domain.member.entity.Member;
import org.sopt.domain.member.dto.request.MemberCreateRequest;
import org.sopt.domain.member.dto.response.MemberGetAllResponse;
import org.sopt.domain.member.dto.response.MemberGetResponse;
import org.sopt.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberCreateResponse createMember(
            MemberCreateRequest request
    ) {
        Member member = request.toEntity();
        memberRepository.save(member);

        return new MemberCreateResponse(member.getToken().toString());
    }

    public Member findMemberById(
            Long memberId
    ) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("해당 사용자가 존재하지 않습니다."));
    }

    public boolean matchesToken(Long memberId, String token) {
        Member member = findMemberById(memberId);
        return member.matchesToken(token);
    }

    @Transactional
    public void deleteMemberById(
            Long memberId
    ) {
        Member member = findMemberById(memberId);
        memberRepository.delete(member);
    }


    /*
    * !구현과제!
    * */
    public MemberGetAllResponse findMembers() {
        List<MemberGetResponse> memberDtos = memberRepository.findAllByOrderByCreatedAtAsc().stream()
                .map(MemberGetResponse::of)
                .toList();

        return MemberGetAllResponse.of(memberDtos.size(), memberDtos);
    }
}
