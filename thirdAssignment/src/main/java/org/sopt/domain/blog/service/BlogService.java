package org.sopt.domain.blog.service;

import lombok.RequiredArgsConstructor;
import org.sopt.common.exception.NotFoundException;
import org.sopt.common.exception.message.ErrorMessage;
import org.sopt.domain.blog.dto.request.BlogCreateRequest;
import org.sopt.domain.blog.entity.Blog;
import org.sopt.domain.blog.repository.BlogRepository;
import org.sopt.domain.member.entity.Member;
import org.sopt.domain.member.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;
    private final MemberService memberService;

    @Transactional
    public String createBlog(BlogCreateRequest request) {
        Member member = memberService.findMemberById(request.memberId());
        Blog blog = request.toEntity(member);
        blogRepository.save(blog);

        return blog.getId().toString();
    }

    public Blog getBlogByMemberId(Long memberId) {
        return blogRepository.findByMemberId(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.BLOG_NOT_FOUND));
    }
}
