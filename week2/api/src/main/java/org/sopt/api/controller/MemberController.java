package org.sopt.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.sopt.api.service.MemberService;
import org.sopt.api.service.dto.MemberCreateDto;
import org.sopt.api.service.dto.MemberFindDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "멤버 생성 API", description = "새로운 멤버를 생성하고 생성된 멤버의 ID를 반환합니다.")
    @ApiResponse(responseCode = "201", description = "멤버 생성 성공", content = @Content(schema = @Schema(implementation = URI.class)))
    @PostMapping
    public ResponseEntity createMember(
            @RequestBody(description = "생성할 멤버의 정보", required = true) MemberCreateDto memberCreateDto
    ) {
        return ResponseEntity.created(URI.create(memberService.createMember(memberCreateDto)))
                .build();
    }

    @Operation(summary = "멤버 조회 API", description = "멤버 ID를 기준으로 멤버 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "멤버 조회 성공", content = @Content(schema = @Schema(implementation = MemberFindDto.class)))
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberFindDto> findMemberById(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.findMemberById(memberId));
    }

    @Operation(summary = "멤버 삭제 API", description = "멤버 ID를 기준으로 멤버 정보를 삭제합니다.")
    @ApiResponse(responseCode = "204", description = "멤버 삭제 성공")
    @DeleteMapping("/{memberId}")
    public ResponseEntity deleteMemberById(@PathVariable Long memberId) {
        memberService.deleteMemberById(memberId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "모든 멤버 조회 API", description = "등록된 모든 멤버를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "모든 멤버 조회 성공", content = @Content(schema = @Schema(implementation = MemberFindDto[].class)))
    @GetMapping("/all")
    public ResponseEntity<List<MemberFindDto>> findAllMembers() {
        return ResponseEntity.ok(memberService.findAllMembers());
    }

}
