package org.sopt.api.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.sopt.api.domain.Member;
import org.sopt.api.repository.MemberRepository;
import org.sopt.api.service.dto.MemberCreateDto;
import org.sopt.api.service.dto.MemberFindDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public String createMember(
            MemberCreateDto memberCreateDto
    ) {
        Member member = Member.create(memberCreateDto.name(), memberCreateDto.part(), memberCreateDto.age());
        memberRepository.save(member);
        return member.getId().toString();
    }

    public MemberFindDto findMemberById(Long memberId) {
        return MemberFindDto.of(memberRepository.findById(memberId).orElseThrow(
                () -> new EntityNotFoundException("ID에 해당하는 사용자가 존재하지 않습니다.")
        ));
    }

    @Transactional
    public void deleteMemberById(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(
                        () -> new EntityNotFoundException("ID에 해당하는 사용자가 존재하지 않습니다.")
                );
        memberRepository.delete(member);
    }

    public List<MemberFindDto> findAllMembers() {
        return memberRepository.findAll().stream()
                .map(MemberFindDto::of)
                .collect(Collectors.toList());
    }

}
