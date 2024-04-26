package org.sopt.cloud.service.service.dto;


import org.sopt.database.domain.Part;

public record MemberCreateDto(
        String name,
        Part part,
        int age
) {
}
