package com.team.chatproject.controller;

import com.team.chatproject.form.LoginForm;
import com.team.chatproject.form.SignupForm;
import com.team.chatproject.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/member")
@AllArgsConstructor
public class MemberController {
    @Autowired
    private MemberService memberService;


    @GetMapping("/signup")
    public String showSignup(SignupForm signupForm){
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
        } catch(DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "/member/signup_form";
        }catch(Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "/member/signup_form";
        }

        return "redirect:/article/list";
    }

    @GetMapping("/login")
    public String showLoginForm(LoginForm loginForm){
        return "/member/login_form";
    }

}


