package org.sopt.database.service.service.dto;


import org.sopt.database.domain.Member;
import org.sopt.database.domain.Part;

public record MemberFindDto(
        String name,
        Part part,
        int age
) {
    public static MemberFindDto of(Member member) {
        return new MemberFindDto(member.getName(), member.getPart(), member.getAge());
    }
}
