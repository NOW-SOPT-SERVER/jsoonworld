package org.sopt.week6to8.service.dto;


import org.sopt.week6to8.domain.Part;

public record MemberCreateDto(
        String name,
        Part part,
        int age
) {
}
