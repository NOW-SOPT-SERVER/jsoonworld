package org.sopt.ofcoursemarket.repository;

import org.sopt.ofcoursemarket.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
