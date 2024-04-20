package org.sopt.database.service.service.dto;

import jakarta.validation.constraints.Size;

//DTO
public record BlogTitleUpdateRequest(
        @Size(max = 10 , message = "블로그 제목이 최대 글자 수(10자)를 초과했습니다.")
        String title
) {
}
