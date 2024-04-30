package org.sopt.domain.blog.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.common.dto.SuccessResponse;
import org.sopt.common.exception.message.SuccessMessage;
import org.sopt.domain.blog.dto.request.BlogCreateRequest;
import org.sopt.domain.blog.dto.response.BlogGetResponse;
import org.sopt.domain.blog.service.BlogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/blog")
public class BlogController {

    private final BlogService blogService;

    @PostMapping
    public ResponseEntity<SuccessResponse> createBlog(
            @RequestBody @Valid BlogCreateRequest request
    ) {
        return ResponseEntity
                .created(URI.create(blogService.createBlog(request)))
                .body(SuccessResponse.of(SuccessMessage.BLOG_CREATE_SUCCESS));
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<BlogGetResponse>> getBlog(
            @RequestParam Long memberId
    ) {
        return ResponseEntity.ok(SuccessResponse.of(
                SuccessMessage.BLOG_GET_SUCCESS,
                BlogGetResponse.of(blogService.getBlogByMemberId(memberId))
        ));
    }
}
