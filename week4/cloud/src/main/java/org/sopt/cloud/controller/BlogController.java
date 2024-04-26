package org.sopt.cloud.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.database.common.dto.SuccessMessage;
import org.sopt.database.common.dto.SuccessStatusResponse;
import org.sopt.database.controller.dto.BlogTitleUpdateRequest;
import org.sopt.database.service.service.BlogService;
import org.sopt.database.service.service.dto.BlogCreateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
            @Valid  @RequestBody BlogTitleUpdateRequest blogTitleUpdateRequest
    ) {
        blogService.updateTitle(blogId, blogTitleUpdateRequest);
        return ResponseEntity.noContent().build();
    }

}
