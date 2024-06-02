package org.sopt.service;

import lombok.RequiredArgsConstructor;
import org.sopt.common.auth.UserAuthentication;
import org.sopt.common.dto.request.MemberJoinRequest;
import org.sopt.common.dto.response.MemberJoinResponse;
import org.sopt.common.auth.jwt.JwtTokenProvider;
import org.sopt.domain.Member;
import org.sopt.common.dto.request.MemberCreateRequest;
import org.sopt.common.dto.response.MemberGetAllResponse;
import org.sopt.common.dto.response.MemberGetResponse;
import org.sopt.common.exception.NotFoundException;
import org.sopt.common.exception.message.ErrorMessage;
import org.sopt.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public String createMemberWithoutAuth(
            MemberCreateRequest request
    ) {
        Member member = request.toEntity();
        memberRepository.save(member);

        return member.getId().toString();
    }

    @Transactional
    public MemberJoinResponse createMember(
            MemberJoinRequest request
    ) {
        Member member = memberRepository.save(request.toEntity());
        String accessToken = getToken(member.getId());

        return MemberJoinResponse.of(accessToken, member.getId().toString());
    }

    private String getToken(Long memberId) {
        UserAuthentication authentication = UserAuthentication.createUserAuthentication(memberId);

        return jwtTokenProvider.issueAccessToken(authentication);
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
