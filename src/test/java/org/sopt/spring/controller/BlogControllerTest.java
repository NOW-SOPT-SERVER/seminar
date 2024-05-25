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

@WebMvcTest(BlogController.class) //SpringBootTest로 서버를 구동시키는 대신 Controller 계층만 테스트
@AutoConfigureMockMvc //Spring Boot 테스트에서 MockMvc를 사용하기 위한 설정을 자동으로 제공하는 어노테이션
public class BlogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /*
    BlogRepository -> BlogService -> MemberService -> MemberRepository
                                                                -> BlogRepository
    */
    @SpyBean
    private BlogService blogService;

    @SpyBean
    private MemberService memberService;

    @MockBean
    private MemberRepository memberRepository;

    @MockBean
    private BlogRepository blogRepository;

    @Autowired
    private ObjectMapper objectMapper; //생성하는 객체를 String JSON 배열로 바꾸기 위해 사용

    @Nested
    class createBlog {
        @Test
        @DisplayName("Blog 생성 실패 테스트")
        public void createBlogFail() throws Exception {

            //given
            String request = objectMapper.writeValueAsString(new BlogCreateRequest("소현이네 블로그", "블로그입니다."));

            //when
            mockMvc.perform(
                            post("/api/v1/blog")
                                    .content(request).header("memberId", 2)
                                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound()) //생성 실패 시나리오로 NotFound가 돌아오는 상황을 테스트
                    .andDo(print()); // 끝난 후 모든 결과를 출력

        }
    }
}

