package org.sopt.week6to8.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.week6to8.auth.PrincipalHandler;
import org.sopt.week6to8.common.dto.SuccessMessage;
import org.sopt.week6to8.common.dto.SuccessStatusResponse;
import org.sopt.week6to8.service.BlogService;
import org.sopt.week6to8.service.dto.BlogCreateRequest;
import org.sopt.week6to8.service.dto.BlogTitleUpdateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;
    private final PrincipalHandler principalHandler;

    @PostMapping("/blog")
    public ResponseEntity createBlog(
            @ModelAttribute BlogCreateRequest blogCreateRequest
    ) {
        return ResponseEntity.created(URI.create(blogService.create(
                principalHandler.getUserIdFromPrincipal(), blogCreateRequest))).build();
    }
    @PatchMapping("/blog/{blogId}/title")
    public ResponseEntity updateBlogTitle(
            @PathVariable Long blogId,
            @Valid @RequestBody BlogTitleUpdateRequest blogTitleUpdateRequest
    ) {
        blogService.updateTitle(blogId, blogTitleUpdateRequest);
        return ResponseEntity.noContent().build();
    }
}
