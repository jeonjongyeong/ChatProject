package com.team.chatproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainControllor {
    // 메인 페이지
    @RequestMapping("/")
    public String main() {
        return "/article/list";
    }

}
