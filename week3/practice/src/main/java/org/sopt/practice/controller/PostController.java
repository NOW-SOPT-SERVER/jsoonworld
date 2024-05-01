package org.sopt.practice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.practice.common.dto.SuccessMessage;
import org.sopt.practice.common.dto.SuccessStatusResponse;
import org.sopt.practice.service.service.PostService;
import org.sopt.practice.service.service.dto.PostCreateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<SuccessStatusResponse> createPost(
            @RequestHeader Long memberId,
            @RequestHeader Long blogId,
            @Valid @RequestBody PostCreateRequest postCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).header(
                        "Location",
                        postService.create(memberId, blogId, postCreateRequest))
                .body(SuccessStatusResponse.of(SuccessMessage.POST_CREATE_SUCCESS));
    }


}
