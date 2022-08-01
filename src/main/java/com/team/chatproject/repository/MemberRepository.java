package com.team.chatproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.team.chatproject.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository <Member,Long> {
    Optional<Member> findByloginId(String loginId);
}
