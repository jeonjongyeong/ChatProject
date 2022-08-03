package com.team.chatproject.controller;

import com.team.chatproject.domain.Member;
import com.team.chatproject.domain.Rq;
import com.team.chatproject.form.LoginForm;
import com.team.chatproject.form.SignupForm;
import com.team.chatproject.repository.MemberRepository;
import com.team.chatproject.service.MemberService;
import com.team.chatproject.util.Ut;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/member")
@AllArgsConstructor
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private Rq rq;


    @GetMapping("/signup")
    public String showSignup(SignupForm signupForm) {
        return "/member/signup_form";
    }

    @PostMapping("/signup")
    public String signup(Model model, @Validated SignupForm signupForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> validationErrors = bindingResult.getFieldErrors()
                    .stream().collect(Collectors.toMap(
                            FieldError::getField,
                            FieldError::getDefaultMessage
                    ));
            if (!validationErrors.isEmpty()) {
                model.addAttribute("validationErrors", validationErrors);
            }
            return "/member/signup_form";
        }
        // 회원가입, 중복아이디, 닉네임 회원가입 불가 구현
        try {
            this.memberService.create(signupForm.getLoginId(), signupForm.getLoginPw(), signupForm.getName(), signupForm.getNickName());
        } catch (DataIntegrityViolationException e) {
            if (memberRepository.findByLoginId(signupForm.getLoginId()).stream().count() != 0) {
                model.addAttribute("loginIdError", signupForm.getLoginId());
            }
            if (memberRepository.findByNickName(signupForm.getNickName()).stream().count() != 0) {
                model.addAttribute("nickNameError", signupForm.getNickName());
            }
            return "/member/signup_form";
        }
        return "redirect:/article/list";
    }

    @RequestMapping("/login")
    public String showLogin() {
        return "member/login_form";
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public String doLogin(String loginId, String loginPw) {
        if (rq.isLogin()) {
            return Ut.jsHistoryBack("이미 로그인되었습니다.");		}

        if (Ut.empty(loginId)) {
            return Ut.jsHistoryBack("loginId(을)를 입력해주세요.");
        }

        if (Ut.empty(loginPw)) {
            return Ut.jsHistoryBack("loginPw(을)를 입력해주세요.");
        }

        Member member = memberService.getMemberByLoginId(loginId);

        if (member == null) {
            return Ut.jsHistoryBack("존재하지 않은 로그인아이디 입니다.");
        }

        if (member.getLoginPw().equals(loginPw) == false) {
            return Ut.jsHistoryBack("비밀번호가 일치하지 않습니다.");
        }

        rq.login(member);

        return Ut.jsReplace(Ut.f("%s님 환영합니다.", member.getNickName()), "/");
    }

    @RequestMapping("/doLogout")
    @ResponseBody
    public String doLogout() {
        if (!rq.isLogin()) {
            return Ut.jsHistoryBack("이미 로그아웃 상태입니다.");
        }

        rq.logout();

        return Ut.jsReplace("로그아웃 되었습니다.","/");
    }

}


