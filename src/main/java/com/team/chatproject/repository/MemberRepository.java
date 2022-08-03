package com.team.chatproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.team.chatproject.domain.Member;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.Optional;

public interface MemberRepository extends JpaRepository <Member,Long> {
    Optional<Member> findByLoginId(String loginId);

    Optional<Member> findByNickName(String nickName);

    int findById(int id);

    Member getMemberByLoginId(String loginId);

    Member getMemberById(int id);
}
