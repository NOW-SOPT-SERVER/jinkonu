package org.sopt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.sopt.common.dto.request.BlogCreateRequest;
import org.sopt.repository.BlogRepository;
import org.sopt.repository.MemberRepository;
import org.sopt.service.BlogService;
import org.sopt.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BlogController.class)
@AutoConfigureMockMvc
class BlogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    BlogService blogService;
    @SpyBean
    MemberService memberService;

    @MockBean
    BlogRepository blogRepository;
    @MockBean
    MemberRepository memberRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Nested
    class createBlog {

        @Test
        @DisplayName("블로그 생성 실패 테스트")
        public void createBlogFailure() throws Exception {
            // given
            String request = objectMapper.writeValueAsString(new BlogCreateRequest("hush hush hush hush", "deep purple", null));

            // when
            mockMvc.perform(
                            post("/api/v1/blog")
                                    .content(request).header("memberId", 1)
                                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andDo(print());
        }
    }
}