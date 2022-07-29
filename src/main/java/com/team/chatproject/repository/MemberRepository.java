package com.team.chatproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.team.chatproject.domain.Member;

public interface MemberRepository extends JpaRepository <Member,Long> {

}
