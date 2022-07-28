package com.team.chatproject.controller;

import com.team.chatproject.domain.Article;

import com.team.chatproject.service.ArticleService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleServise;


    // 전체 조회
    @RequestMapping("/list")
    public String showList(Model model) {
        List<Article> articles = articleServise.getList();
        model.addAttribute("articles", articles);
        log.info(articles.toString());
        return "/article/article_list";
    }

    // 상세 조회
    @RequestMapping("/detail/{id}")
    public String showDetail(Model model, @PathVariable Long id) {
        Article article = articleServise.getDetail(id);
        model.addAttribute("article", article);
        return "/article/article_detail";
    }

    // 게시글 생성
    @GetMapping("/new")
    public String newArticle() {
        return "/article/article_new";
    }

    @PostMapping("/create")
    public String createArticle(@RequestParam String title,@RequestParam String body) {
        this.articleServise.create(title, body);
        return "redirect:/article/list";
    }




}
