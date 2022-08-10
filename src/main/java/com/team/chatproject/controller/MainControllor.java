package com.team.chatproject.controller;

import com.team.chatproject.domain.Rq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainControllor {

    @Autowired
    private Rq rq;
    // 메인 페이지
    @RequestMapping("/")
    public String main() {
        return "/commen/main";
    }

}
