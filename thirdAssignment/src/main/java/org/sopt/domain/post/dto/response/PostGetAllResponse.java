package org.sopt.domain.post.dto.response;

import org.sopt.domain.post.entity.Post;

import java.util.List;

public record PostGetAllResponse(
        int count,
        List<PostGetResponse> posts
) {
    public static PostGetAllResponse of(List<Post> posts) {
        return new PostGetAllResponse(
                posts.size(),
                posts.stream()
                        .map(PostGetResponse::of)
                        .toList()
        );
    }
}
