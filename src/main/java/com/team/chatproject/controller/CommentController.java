package com.team.chatproject.controller;

import com.team.chatproject.domain.Article;
import com.team.chatproject.form.CommentForm;
import com.team.chatproject.service.ArticleService;
import com.team.chatproject.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/comment")
@AllArgsConstructor
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    ArticleService articleService;

    @PostMapping("/create/{id}")
    public String createComment(Model model, @PathVariable("id") Long id,
                                @Validated CommentForm commentForm, BindingResult bindingResult) {
        Article article = this.articleService.getDetail(id);
        if (bindingResult.hasErrors()) {
            Map<String, String> validationErrors = bindingResult.getFieldErrors()
                    .stream().collect(Collectors.toMap(
                            FieldError::getField,
                            FieldError::getDefaultMessage
                    ));
            if (!validationErrors.isEmpty()) {
                model.addAttribute("validationErrors", validationErrors);
            }
            model.addAttribute("article", article);
            return "/article/article_detail";
        }
        // 질문만들기
        this.commentService.create(article, commentForm.getComments());
        return String.format("redirect:/article/detail/%s", id);
    }
}
