package com.santaduck.tbd.domain.member.repository;

import com.santaduck.tbd.domain.member.entity.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MemberRepository extends CrudRepository<Member, Long> {
    Optional<Member> findByNickname(String nickname);
    Optional<Member> findByEmail(String email);
    Optional<Member> findByMemberId(String memberId);
}
