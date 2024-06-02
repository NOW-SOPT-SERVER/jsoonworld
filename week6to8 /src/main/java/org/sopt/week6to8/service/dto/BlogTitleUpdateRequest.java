package org.sopt.week6to8.service.dto;


import jakarta.validation.constraints.Size;

public record BlogTitleUpdateRequest(
        @Size(max = 5, message = "블로그 제목 최대 글자 수 (5자)를 초과했습니다.")
        String title
) {
}
