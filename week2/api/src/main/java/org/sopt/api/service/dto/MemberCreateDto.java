package org.sopt.api.service.dto;

import org.sopt.api.domain.Part;

public record MemberCreateDto (
        String name,
        Part part,
        int age
){
}
