package com.team.chatproject.service;

import com.team.chatproject.domain.Member;
import com.team.chatproject.form.SignupForm;
import com.team.chatproject.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public void create(SignupForm signupForm) {
        Member newMember = new Member();

        newMember.setLoginId(signupForm.getLoginId());
        newMember.setLoginPw(signupForm.getLoginPw());

        newMember.setName(signupForm.getName());
        newMember.setNikName(signupForm.getNikName());

        newMember.setRegDate(LocalDateTime.now());
        newMember.setUpdateDate(LocalDateTime.now());

        this.memberRepository.save(newMember);
    }
}
