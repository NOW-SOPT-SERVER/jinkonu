package org.sopt.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.sopt.common.exception.InvalidTokenException;
import org.sopt.common.exception.message.ErrorMessage;
import org.sopt.domain.blog.entity.Blog;
import org.sopt.domain.blog.service.BlogService;
import org.sopt.domain.member.service.MemberService;
import org.sopt.domain.post.dto.request.PostCreateRequest;
import org.sopt.domain.post.dto.response.PostGetAllResponse;
import org.sopt.domain.post.entity.Post;
import org.sopt.domain.post.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final BlogService blogService;
    private final MemberService memberService;

    public String createPost(PostCreateRequest request) {
        if (memberService.matchesToken(request.memberId(), request.token())) {
            Blog blog = blogService.getBlogByMemberId(request.memberId());
            Post post = request.toEntity(blog);
            postRepository.save(post);

            return post.getId().toString();
        }

        throw new InvalidTokenException(ErrorMessage.INVALID_TOKEN);
    }

    public List<Post> getAllPostsByMemberId(Long memberId) {
        Blog blog = blogService.getBlogByMemberId(memberId);
        List<Post> posts = postRepository.findAllByBlogId(blog.getId());

        return posts;
    }
}
