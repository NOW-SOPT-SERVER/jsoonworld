package org.sopt.ofcoursemarket.service;

import org.sopt.ofcoursemarket.domain.Member;
import org.sopt.ofcoursemarket.dto.MemberCreateDto;
import org.sopt.ofcoursemarket.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Long createMember(MemberCreateDto memberCreateDto) {
        Member member = Member.builder()
                .userId(memberCreateDto.userId())
                .name(memberCreateDto.name())
                .age(memberCreateDto.age())
                .build();
        return memberRepository.save(member).getId();
    }
}
