package com.team.chatproject.controller;

import com.team.chatproject.domain.Article;
import com.team.chatproject.domain.Comment;
import com.team.chatproject.service.ArticleService;
import com.team.chatproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    ArticleService articleService;

    @PostMapping("/create/{id}")
    public String createComment(Model model, @PathVariable("id") Long id, @RequestParam String comments) {
        Article article = this.articleService.getDetail(id);
        // 질문만들기
        this.commentService.create(article, comments);
        return String.format("/article/detail/%s", id);
    }

}
