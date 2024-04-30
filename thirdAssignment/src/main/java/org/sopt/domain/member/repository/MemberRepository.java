package org.sopt.domain.member.repository;

import org.sopt.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findAllByOrderByCreatedAtAsc();
    List<Member> findAllByOrderByCreatedAtDesc();
}
