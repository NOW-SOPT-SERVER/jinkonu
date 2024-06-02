package org.sopt.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.sopt.domain.Member;
import org.sopt.common.dto.request.MemberCreateRequest;
import org.sopt.common.dto.response.MemberGetAllResponse;
import org.sopt.common.dto.response.MemberGetResponse;
import org.sopt.exception.NotFoundException;
import org.sopt.exception.message.ErrorMessage;
import org.sopt.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public String createMember(
            MemberCreateRequest request
    ) {
        Member member = request.toEntity();
        memberRepository.save(member);

        return member.getId().toString();
    }

    public MemberGetResponse findMemberById(
            Long memberId
    ) {
        return MemberGetResponse.of(memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND_BY_ID_EXCEPTION)));
    }

    public Member findById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND_BY_ID_EXCEPTION));
    }

    @Transactional
    public void deleteMemberById(
            Long memberId
    ) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND_BY_ID_EXCEPTION));

        memberRepository.delete(member);
    }


    /*
    * !구현과제!
    * */
    public MemberGetAllResponse findMembers() {
        List<MemberGetResponse> memberDtos = memberRepository.findAll().stream()
                .map(MemberGetResponse::of)
                .toList();

        return MemberGetAllResponse.of(memberDtos.size(), memberDtos);
    }
}
