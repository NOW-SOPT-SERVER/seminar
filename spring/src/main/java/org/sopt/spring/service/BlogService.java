package org.sopt.spring.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.sopt.spring.common.dto.ErrorMessage;
import org.sopt.spring.domain.Blog;
import org.sopt.spring.domain.Member;
import org.sopt.spring.exception.NotFoundException;
import org.sopt.spring.repository.BlogRepository;
import org.sopt.spring.service.dto.BlogCreateRequest;
import org.sopt.spring.service.dto.BlogTitleUpdateRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final MemberService memberService;


    public String create(Long memberId, BlogCreateRequest blogCreateRequest) {
        Member member = memberService.findById(memberId);
        Blog blog = blogRepository.save(Blog.create(member, blogCreateRequest));
        return blog.getId().toString();
    }

    private Blog findById(Long blogId) {
        return blogRepository.findById(blogId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.BLOG_NOT_FOUND)
        );
    }

    @Transactional
    public void updateTitle(Long blogId, BlogTitleUpdateRequest blogTitleUpdateRequest) {
        Blog blog = findById(blogId);
        blog.updateTitle(blogTitleUpdateRequest);
    }
}
