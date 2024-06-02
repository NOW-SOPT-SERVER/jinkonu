package org.sopt.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.common.dto.request.MemberCreateRequest;
import org.sopt.common.dto.response.MemberGetAllResponse;
import org.sopt.common.dto.response.MemberGetResponse;
import org.sopt.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity createMember(
            @RequestBody MemberCreateRequest request
    ) {
        return ResponseEntity.created(URI.create(memberService.createMember(request)))
                .build();
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
