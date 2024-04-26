package org.sopt.api.service.dto;

import org.sopt.api.domain.Member;
import org.sopt.api.domain.Part;

public record MemberFindDto(
        String name,
        Part part,
        int age
) {
    public static MemberFindDto of(Member member) {
        return new MemberFindDto(member.getName(), member.getPart(), member.getAge());
    }
}
