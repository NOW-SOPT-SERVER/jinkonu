package org.sopt.domain.post.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.common.dto.SuccessResponse;
import org.sopt.common.exception.message.SuccessMessage;
import org.sopt.domain.post.dto.request.PostCreateRequest;
import org.sopt.domain.post.dto.response.PostGetAllResponse;
import org.sopt.domain.post.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<SuccessResponse> createPost(
            @RequestBody PostCreateRequest request
    ) {
        return ResponseEntity
                .created(URI.create(postService.createPost(request)))
                .body(SuccessResponse.of(SuccessMessage.POST_CREATE_SUCCESS));
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<PostGetAllResponse>> findAllPosts(
            @RequestParam Long memberId
    ) {
        return ResponseEntity.ok(SuccessResponse.of(
                SuccessMessage.POST_GET_ALL_SUCCESS,
                PostGetAllResponse.of(postService.getAllPostsByMemberId(memberId))
        ));
    }
}
