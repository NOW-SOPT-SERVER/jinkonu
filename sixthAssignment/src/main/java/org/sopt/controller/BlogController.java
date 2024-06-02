package org.sopt.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.common.dto.request.BlogCreateRequest;
import org.sopt.common.dto.request.BlogUpdateRequest;
import org.sopt.common.dto.response.BlogGetAllResponse;
import org.sopt.common.dto.response.SuccessMessage;
import org.sopt.common.dto.response.SuccessStatusResponse;
import org.sopt.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class BlogController {

    private final BlogService blogService;

    @PostMapping("/blog")
    public ResponseEntity<SuccessStatusResponse> createBlog(
            @RequestHeader Long memberId,
            @RequestBody BlogCreateRequest request
    ) {
        Long blogId = blogService.createBlog(memberId, request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", String.valueOf(blogId))
                .body(SuccessStatusResponse.of(SuccessMessage.BLOG_CREATE_SUCCESS));
    }

    @GetMapping("/blogs")
    public ResponseEntity<BlogGetAllResponse> getAllBlogs(
    ) {
        return ResponseEntity.ok(blogService.getAllBlogs());
    }

    @PatchMapping("/blog/{blogId}")
    public ResponseEntity updateBlogTitle(
            @PathVariable Long blogId,
            @RequestBody @Valid BlogUpdateRequest request
    ) {
        blogService.updateTitle(blogId, request);

        return ResponseEntity.noContent().build();
    }
}
