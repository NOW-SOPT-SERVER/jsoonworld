package org.sopt.practice.service.service;

import lombok.RequiredArgsConstructor;
import org.sopt.practice.common.dto.ErrorMessage;
import org.sopt.practice.domain.Blog;
import org.sopt.practice.domain.Post;
import org.sopt.practice.exception.ForbiddenException;
import org.sopt.practice.exception.NotFoundException;
import org.sopt.practice.repository.BlogRepository;
import org.sopt.practice.repository.PostRepository;
import org.sopt.practice.service.service.dto.PostCreateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final BlogRepository blogRepository;
    private final PostRepository postRepository;

    @Transactional
    public String create(Long memberId, Long blogId, PostCreateRequest postCreateRequest) {

        validateBlogOwnership(memberId, blogId);

        Blog blog = blogRepository.findById(blogId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.BLOG_NOT_FOUND)
        );

        Post post = postRepository.save(Post.create(blog, postCreateRequest.title(), postCreateRequest.content()));
        return post.getId().toString();

    }

    private void validateBlogOwnership(Long memberId, Long blogId) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.BLOG_NOT_FOUND));

        if(!blog.getMember().getId().equals(memberId)) {
            throw new ForbiddenException(ErrorMessage.FORBIDDEN_MEMBER_ACCESS);
        }
    }
    @Transactional(readOnly = true)
    public PostFindDto findPostById(Long postId) {
        return PostFindDto.of(postRepository.findByIdOrThrow(postId));
    }

}
