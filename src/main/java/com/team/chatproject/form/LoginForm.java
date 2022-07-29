package com.team.chatproject.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class LoginForm {
    @NotEmpty(message = "아이디는필수항목입니다.")
    private String loginId;

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String loginPw;
}
