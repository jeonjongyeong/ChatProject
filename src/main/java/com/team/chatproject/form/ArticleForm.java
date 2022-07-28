package com.team.chatproject.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ArticleForm {

    @NotEmpty(message = "제목은 필수항목입니다.")
    private String title;

    @NotEmpty(message = "내용은 필수항목입니다.")
    private String body;

}
