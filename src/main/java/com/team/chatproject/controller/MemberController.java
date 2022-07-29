package com.team.chatproject.controller;

import com.team.chatproject.form.LoginForm;
import com.team.chatproject.form.SignupForm;
import com.team.chatproject.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    MemberService memberService;


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
        // 회원가입
        this.memberService.create(signupForm);
        return "redirect:/article/list";
    }

    @GetMapping("/login")
    public String showloginForm(LoginForm loginForm){
        return "/member/login_form";
    }

}


