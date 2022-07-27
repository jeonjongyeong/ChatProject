package com.team.chatproject.controller;

import com.team.chatproject.servise.ArticleServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleServise articleServise;

    @RequestMapping("/create")
    @ResponseBody
    public String doCreateArticle(long memberId,String title,String body){
        articleServise.create(memberId,title,body);
        return "추가 되었습니다.";
    }

    @RequestMapping("/list")
    @ResponseBody
    public String showList() {
        return "hello world!";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String showtest() {
        return "hello world!";
    }




}
