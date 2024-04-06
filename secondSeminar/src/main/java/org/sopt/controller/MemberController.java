package org.sopt.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.service.MemberService;
import org.sopt.service.dto.MemberCreateDto;
import org.sopt.service.dto.MemberFindDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity postMember(
            @RequestBody MemberCreateDto memberCreate
    ) {
        return ResponseEntity.created(
                URI.create(memberService.createMember(memberCreate))
        ).build();
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberFindDto> findMemberById(
            @PathVariable("memberId") Long id
    ) {
        return ResponseEntity.ok(
                memberService.findMemberById(id)
        );
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity deleteMemberById(
            @PathVariable("memberId") Long id
    ) {
        memberService.deleteMemberById(id);

        return ResponseEntity.noContent()
                .build();
    }
}
