package com.team.chatproject.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SignupForm {
    @Size(min=3, max=25)
    @NotEmpty(message = "아이디는필수항목입니다.")
    private String loginId;

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String loginPw;

    @NotEmpty(message = "이름은 필수항목입니다.")
    private String name;

    @NotEmpty(message = "닉네임 필수항목입니다.")
    private String nickName;

}
