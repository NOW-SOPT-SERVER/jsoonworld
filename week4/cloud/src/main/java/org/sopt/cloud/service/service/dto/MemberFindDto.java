package org.sopt.cloud.service.service.dto;


import org.sopt.cloud.domain.Member;
import org.sopt.cloud.domain.Part;

public record MemberFindDto(
        String name,
        Part part,
        int age
) {
    public static MemberFindDto of(Member member) {
        return new MemberFindDto(member.getName(), member.getPart(), member.getAge());
    }
}
