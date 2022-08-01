package com.team.chatproject.service;

import com.team.chatproject.domain.Member;
import com.team.chatproject.repository.MemberRepository;
import com.team.chatproject.role.MemberRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberSecurityService implements UserDetailsService {

    @Autowired
    public MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Optional<Member> _siteMember = this.memberRepository.findByloginId(loginId);
        if (_siteMember.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
        }
        Member member = _siteMember.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        if ("admin".equals(loginId)) {
            authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(MemberRole.USER.getValue()));
        }
        return new User(member.getLoginId(), member.getLoginPw(), authorities);
    }
}