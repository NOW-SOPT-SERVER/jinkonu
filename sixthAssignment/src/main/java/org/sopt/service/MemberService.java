package org.sopt.service;

import lombok.RequiredArgsConstructor;
import org.sopt.common.auth.dto.AuthTokenSet;
import org.sopt.common.auth.redis.service.TokenService;
import org.sopt.common.dto.request.MemberJoinRequest;
import org.sopt.common.dto.response.MemberJoinResponse;
import org.sopt.common.auth.jwt.JwtTokenProvider;
import org.sopt.common.dto.response.MemberTokenRefreshResponse;
import org.sopt.common.exception.UnauthorizedException;
import org.sopt.domain.Member;
import org.sopt.common.dto.response.MemberGetAllResponse;
import org.sopt.common.dto.response.MemberGetResponse;
import org.sopt.common.exception.NotFoundException;
import org.sopt.common.exception.message.ErrorMessage;
import org.sopt.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

import static org.sopt.common.auth.dto.AuthTokenSet.Type.ACCESS_TOKEN;
import static org.sopt.common.auth.dto.AuthTokenSet.Type.REFRESH_TOKEN;
import static org.sopt.common.auth.jwt.JwtValidationType.VALID_JWT;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenService tokenService;

    @Transactional
    public MemberJoinResponse createMember(
            MemberJoinRequest request
    ) {
        Member member = memberRepository.save(request.toEntity());
        AuthTokenSet token = getTokenSet(member.getId());

        return MemberJoinResponse.of(token, member.getId().toString());
    }

    private AuthTokenSet getTokenSet(Long memberId) {
        return AuthTokenSet.of(
                jwtTokenProvider.issueToken(memberId, ACCESS_TOKEN),
                jwtTokenProvider.issueToken(memberId, REFRESH_TOKEN)
        );
    }

    public MemberTokenRefreshResponse refreshAccessToken(String refreshToken) {
        if (jwtTokenProvider.validateToken(refreshToken) != VALID_JWT)
            throw new UnauthorizedException(ErrorMessage.JWT_UNAUTHORIZED_EXCEPTION);

        Long memberId = jwtTokenProvider.getMemberIdFromToken(refreshToken);
        String accessToken = jwtTokenProvider.issueToken(memberId, ACCESS_TOKEN);
        return MemberTokenRefreshResponse.of(accessToken);
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
