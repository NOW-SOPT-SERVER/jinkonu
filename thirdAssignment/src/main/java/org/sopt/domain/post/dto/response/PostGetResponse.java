package org.sopt.domain.post.dto.response;

import org.sopt.domain.post.entity.Post;

public record PostGetResponse(
        String blogTitle,
        String title,
        String content
) {
    public static PostGetResponse of(Post post) {
        return new PostGetResponse(post.getBlog().getTitle(), post.getTitle(), post.getContent());
    }
}
