package org.sopt.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.common.dto.SuccessResponse;
import org.sopt.common.exception.message.SuccessMessage;
import org.sopt.domain.member.dto.request.MemberCreateRequest;
import org.sopt.domain.member.dto.response.MemberCreateResponse;
import org.sopt.domain.member.dto.response.MemberGetAllResponse;
import org.sopt.domain.member.dto.response.MemberGetResponse;
import org.sopt.domain.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<SuccessResponse<MemberCreateResponse>> createMember(
            @RequestBody MemberCreateRequest request
    ) {
        return ResponseEntity.ok(SuccessResponse.of(
                SuccessMessage.MEMBER_CREATE_SUCCESS,
                memberService.createMember(request)
        ));
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<SuccessResponse<MemberGetResponse>> findMemberById(
            @PathVariable Long memberId
    ) {
        return ResponseEntity.ok(SuccessResponse.of(
                SuccessMessage.MEMBER_GET_SUCCESS,
                MemberGetResponse.of(memberService.findMemberById(memberId))
        ));
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity deleteMemberById(
            @PathVariable Long memberId
    ) {
        memberService.deleteMemberById(memberId);

        return ResponseEntity.ok(SuccessResponse.of(
                SuccessMessage.MEMBER_DELETE_SUCCESS
        ));
    }


    /*
     * !구현과제!
     * */
    @GetMapping
    public ResponseEntity<SuccessResponse<MemberGetAllResponse>> findMembers() {
        return ResponseEntity.ok(SuccessResponse.of(
                SuccessMessage.MEMBER_GET_SUCCESS,
                memberService.findMembers()
        ));
    }
}
