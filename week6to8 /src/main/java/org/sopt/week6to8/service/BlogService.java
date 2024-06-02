package org.sopt.week6to8.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.sopt.week6to8.common.dto.ErrorMessage;
import org.sopt.week6to8.domain.Blog;
import org.sopt.week6to8.domain.Member;
import org.sopt.week6to8.exception.NotFoundException;
import org.sopt.week6to8.exteranl.S3Service;
import org.sopt.week6to8.repository.BlogRepository;
import org.sopt.week6to8.service.dto.BlogCreateRequest;
import org.sopt.week6to8.service.dto.BlogTitleUpdateRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final MemberService memberService;
    private final S3Service s3Service;
    private static final String BLOG_S3_UPLOAD_FOLER = "blog/";


    @Transactional
    public String create(Long memberId, BlogCreateRequest createRequest) {
        //member찾기
        Member member = memberService.findById(memberId);
        try {
            Blog blog = blogRepository.save(Blog.create(member, createRequest.title(), createRequest.description(),
                    s3Service.uploadImage(BLOG_S3_UPLOAD_FOLER, createRequest.image())));
            return blog.getId().toString();
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e.getMessage());
        }
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
