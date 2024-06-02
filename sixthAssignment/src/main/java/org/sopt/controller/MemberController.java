package org.sopt.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.common.dto.request.MemberJoinRequest;
import org.sopt.common.dto.response.MemberGetAllResponse;
import org.sopt.common.dto.response.MemberGetResponse;
import org.sopt.common.dto.response.MemberJoinResponse;
import org.sopt.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberJoinResponse> postMember(
            @RequestBody MemberJoinRequest request
    ) {
        MemberJoinResponse memberJoinResponse = memberService.createMember(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", memberJoinResponse.userId())
                .body(memberJoinResponse);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberGetResponse> findMemberById(
            @PathVariable Long memberId
    ) {
        return ResponseEntity.ok(memberService.findMemberById(memberId));
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity deleteMemberById(
            @PathVariable Long memberId
    ) {
        memberService.deleteMemberById(memberId);

        return ResponseEntity.noContent().build();
    }


    /*
     * !구현과제!
     * */
    @GetMapping
    public ResponseEntity<MemberGetAllResponse> findMembers() {
        return ResponseEntity.ok(memberService.findMembers());
    }
}
