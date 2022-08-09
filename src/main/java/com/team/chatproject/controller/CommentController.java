package com.team.chatproject.controller;

import com.team.chatproject.domain.Article;
import com.team.chatproject.domain.Comment;
import com.team.chatproject.domain.Rq;
import com.team.chatproject.form.CommentForm;
import com.team.chatproject.service.ArticleService;
import com.team.chatproject.service.CommentService;
import com.team.chatproject.util.Ut;
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
    @Autowired
    private Rq rq;


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

    // 수정
    @GetMapping("/modify/{id}")
    public String getComment(@PathVariable Long id, Model model) {
        Comment comment = commentService.getComment(id);
        model.addAttribute("comments", comment);
        return "/comment/comment_modify";
    }

    @PostMapping("/modify/{id}")
    public String modifyComment(@PathVariable Long id,
                                Model model,
                                @Validated CommentForm commentForm,
                                BindingResult bindingResult) {
        Comment comment = commentService.getComment(id);
        if (bindingResult.hasErrors()) {
            Map<String, String> validationErrors = bindingResult.getFieldErrors()
                    .stream().collect(Collectors.toMap(
                            FieldError::getField,
                            FieldError::getDefaultMessage
                    ));
            if (!validationErrors.isEmpty()) {
                model.addAttribute("validationErrors", validationErrors);
            }
            return "/comment/comment_modify";
        }
        this.commentService.update(id, commentForm.getComments());
        return "redirect:/article/detail/" + comment.getArticle().getId();
    }




    // 삭제
    @GetMapping("/delete/{id}")
    public String commentDelete(@PathVariable long id) {
        Comment comment = this.commentService.getComment(id);
        this.commentService.delete(comment);
        return String.format("redirect:/article/detail/%s", comment.getArticle().getId());
    }
}
