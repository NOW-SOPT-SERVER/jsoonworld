package org.sopt.practice.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.practice.common.dto.SuccessMessage;
import org.sopt.practice.common.dto.SuccessStatusResponse;
import org.sopt.practice.service.service.BlogService;
import org.sopt.practice.service.service.dto.BlogCreateRequest;
import org.sopt.practice.service.service.dto.BlogTitleUpdateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @PostMapping("/blog")
    public ResponseEntity<SuccessStatusResponse> createBlog(
            @RequestHeader Long memberId,
            @RequestBody BlogCreateRequest blogCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).header(
                        "Location",
                        blogService.create(memberId, blogCreateRequest))
                .body(SuccessStatusResponse.of(SuccessMessage.BLOG_CREATE_SUCCESS));
    }

    @PatchMapping("/blog/{blogId}/title")
    public ResponseEntity updateBlogTitle(
            @PathVariable Long blogId,
            @Validated @RequestBody BlogTitleUpdateRequest blogTitleUpdateRequest
    ) {
        blogService.updateTitle(blogId, blogTitleUpdateRequest);
        return ResponseEntity.noContent().build();
    }

}
