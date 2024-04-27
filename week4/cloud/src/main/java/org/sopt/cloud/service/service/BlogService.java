package org.sopt.cloud.service.service;

import lombok.RequiredArgsConstructor;
import org.sopt.cloud.common.ErrorMessage;
import org.sopt.cloud.domain.Blog;
import org.sopt.cloud.domain.Member;
import org.sopt.cloud.exception.NotFoundException;
import org.sopt.cloud.repository.BlogRepository;
import org.sopt.cloud.service.service.dto.BlogCreateRequest;
import org.sopt.cloud.service.service.dto.BlogTitleUpdateRequest;
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
