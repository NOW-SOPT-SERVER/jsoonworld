package org.sopt.week6to8.service.dto;

import org.springframework.web.multipart.MultipartFile;

public record BlogCreateRequest(
        String title,
        String description,
        MultipartFile image
) {
}
