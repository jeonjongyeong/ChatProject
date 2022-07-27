package com.team.chatproject.controller;

import com.team.chatproject.domain.Article;
import com.team.chatproject.servise.ArticleServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


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

    // 전체 조회
    @RequestMapping("/list")
    public String showList(Model model) {
        List<Article> articles = articleServise.getList();
        model.addAttribute("articles", articles);
        return "/article/article_list";
    }

    // 상세 조회
    @RequestMapping("/detail/{id}")
    public String showDetail(Model model, @PathVariable Long id) {
        Article article = articleServise.getDetail(id);
        model.addAttribute("article", article);
        return "/article/article_detail";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String showtest() {
        return "hello world!";
    }




}
