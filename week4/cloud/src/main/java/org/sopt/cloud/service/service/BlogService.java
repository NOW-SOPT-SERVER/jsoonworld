package org.sopt.cloud.service.service;

import lombok.RequiredArgsConstructor;
import org.sopt.database.common.ErrorMessage;
import org.sopt.database.controller.dto.BlogTitleUpdateRequest;
import org.sopt.database.domain.Blog;
import org.sopt.database.domain.Member;
import org.sopt.database.exception.NotFoundException;
import org.sopt.database.repository.BlogRepository;
import org.sopt.database.service.service.dto.BlogCreateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void updateTitle(Long blogId, BlogTitleUpdateRequest blogTitleUpdateRequest) {
        Blog blog = findBlogById(blogId);
        blog.updateTitle(blogTitleUpdateRequest.title());
    }

    private Blog findBlogById(Long blogId) {
        return blogRepository.findById(blogId).orElseThrow(() -> new NotFoundException(ErrorMessage.BLOG_NOT_FOUND));
    }

}
