package org.sopt.practice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.practice.common.dto.SuccessMessage;
import org.sopt.practice.common.dto.SuccessStatusResponse;
import org.sopt.practice.service.service.PostService;
import org.sopt.practice.service.service.dto.PostCreateRequest;
import org.sopt.practice.service.service.dto.PostFindAllDto;
import org.sopt.practice.service.service.dto.PostFindDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/posts/{postId}")
        public ResponseEntity<SuccessStatusResponse<PostFindDto>> findPost(@PathVariable Long postId) {
            return ResponseEntity.status(HttpStatus.OK).body(SuccessStatusResponse.of(
                SuccessMessage.POST_FIND_SUCCESS,
                postService.findPostById(postId)));
    }

    @GetMapping("/posts")
    public ResponseEntity<SuccessStatusResponse<List<PostFindAllDto>>> findPosts() {
        return ResponseEntity.status(HttpStatus.OK).body(SuccessStatusResponse.of(
                SuccessMessage.POST_FIND_SUCCESS,
                postService.findAllPosts()));
    }
}
