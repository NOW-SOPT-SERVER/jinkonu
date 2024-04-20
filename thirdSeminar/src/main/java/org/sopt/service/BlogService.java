package org.sopt.service;

import lombok.RequiredArgsConstructor;
import org.sopt.common.dto.request.BlogCreateRequest;
import org.sopt.common.dto.request.BlogUpdateRequest;
import org.sopt.common.dto.response.BlogGetAllResponse;
import org.sopt.domain.Blog;
import org.sopt.domain.Member;
import org.sopt.exception.NotFoundException;
import org.sopt.exception.message.ErrorMessage;
import org.sopt.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private final MemberService memberService;

    public Long createBlog(Long memberId, BlogCreateRequest request) {
        Member member = memberService.findById(memberId);

        return blogRepository.save(request.toEntity(member))
                .getId();
    }

    public BlogGetAllResponse getAllBlogs() {
        return BlogGetAllResponse.of(blogRepository.findAll());
    }

    @Transactional
    public void updateTitle(Long blogId, BlogUpdateRequest request) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.BLOG_NOT_FOUND_BY_ID_EXCEPTION));

        blog.update(request);
    }
}
