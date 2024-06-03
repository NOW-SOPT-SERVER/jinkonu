package org.sopt.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.common.dto.request.MemberJoinRequest;
import org.sopt.common.dto.response.MemberGetAllResponse;
import org.sopt.common.dto.response.MemberGetResponse;
import org.sopt.common.dto.response.MemberJoinResponse;
import org.sopt.common.dto.response.MemberTokenRefreshResponse;
import org.sopt.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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

    @GetMapping("/refresh")
    public ResponseEntity<MemberTokenRefreshResponse> refreshAccessToken(
            @RequestHeader("Authorization") String refreshToken
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(memberService.refreshAccessToken(refreshToken));
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberGetResponse> findMemberById(
            @PathVariable Long memberId
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(MemberGetResponse.of(memberService.findById(memberId)));
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
