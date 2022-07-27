package com.team.chatproject.controller;

import com.team.chatproject.servise.ArticleServise;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/article")
public class ArticleController {
    ArticleServise articleServise;
    ArticleController(ArticleServise articleServise){
        this.articleServise = articleServise;
    }
    @RequestMapping("/create")
    @ResponseBody
    public String doCreateArticle(long memberId,String title,String body){
        articleServise.create(memberId,title,body);
        return "추가 되었습니다.";
    }




}
