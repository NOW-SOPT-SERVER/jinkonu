package org.sopt.service;

import lombok.RequiredArgsConstructor;
import org.sopt.common.dto.request.BlogCreateRequest;
import org.sopt.common.dto.request.BlogUpdateRequest;
import org.sopt.common.dto.response.BlogGetAllResponse;
import org.sopt.domain.Blog;
import org.sopt.domain.Member;
import org.sopt.common.exception.NotFoundException;
import org.sopt.common.exception.message.ErrorMessage;
import org.sopt.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class BlogService {

    private static final String BLOG_S3_UPLOAD_FOLDER = "blog/";

    private final BlogRepository blogRepository;
    private final MemberService memberService;
    private final S3Service s3Service;

    public Long createBlog(Long memberId, BlogCreateRequest request) {
        String imageUrl;
        Member member = memberService.findById(memberId);

        try {
            imageUrl = s3Service.uploadImage(BLOG_S3_UPLOAD_FOLDER, request.image());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return blogRepository.save(request.toEntity(member, imageUrl))
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
