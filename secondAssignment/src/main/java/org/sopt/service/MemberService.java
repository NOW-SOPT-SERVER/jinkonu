package org.sopt.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.sopt.domain.Member;
import org.sopt.dto.request.MemberCreateRequest;
import org.sopt.dto.response.MemberGetAllResponse;
import org.sopt.dto.response.MemberGetResponse;
import org.sopt.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

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
                .orElseThrow(() -> new EntityNotFoundException("ID에 해당하는 사용자가 존재하지 않습니다.")));
    }

    @Transactional
    public void deleteMemberById(
            Long memberId
    ) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("ID에 해당하는 사용자가 존재하지 않습니다."));

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
