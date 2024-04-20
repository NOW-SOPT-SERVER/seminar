package org.sopt.spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.sopt.spring.repository.BlogRepository;
import org.sopt.spring.repository.MemberRepository;
import org.sopt.spring.service.BlogService;
import org.sopt.spring.service.MemberService;
import org.sopt.spring.service.dto.BlogCreateRequest;
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
public class BlogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private BlogService blogService;

    @SpyBean
    private MemberService memberService;

    @MockBean
    private BlogRepository blogRepository;

    @MockBean
    private MemberRepository memberRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Nested
    class createBlog {
        @Test
        @DisplayName("Blog 생성 실패 테스트")
        public void createBlogFail() throws Exception {
            //given
            String request = objectMapper.writeValueAsString(new BlogCreateRequest("소현이네 블로그", "블로그"));;

            mockMvc.perform(
                    post("/api/v1/blog")
                            .content(request).header("memberId",2).contentType(MediaType.APPLICATION_JSON)
            ).andExpect(status().isNotFound())
                    .andDo(print());
        }
    }
}
