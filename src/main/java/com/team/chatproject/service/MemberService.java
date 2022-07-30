package com.team.chatproject.service;

import com.team.chatproject.domain.Member;
import com.team.chatproject.form.SignupForm;
import com.team.chatproject.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public void create(String loginId, String password, String name, String nickname  ) {
        Member newMember = new Member();
        newMember.setLoginId(loginId);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        newMember.setLoginPw(passwordEncoder.encode(password));
        newMember.setName(name);
        newMember.setNickName(nickname);
        newMember.setRegDate(LocalDateTime.now());
        newMember.setUpdateDate(LocalDateTime.now());

        this.memberRepository.save(newMember);
    }
}
