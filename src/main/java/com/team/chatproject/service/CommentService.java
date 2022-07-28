package com.team.chatproject.service;

import com.team.chatproject.domain.Article;
import com.team.chatproject.domain.Comment;
import com.team.chatproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    public Comment getComment(Article articleId) {
        Long id = articleId.getId();
        Comment comment = commentRepository.findByArticleId(id);
        return comment;
    }

    public void create(Article article, String comments) {
        Comment newComment = new Comment();
        newComment.setComments(comments);
        newComment.setArticle(article);
        newComment.setRegDate(LocalDateTime.now());
        newComment.setUpdateDate(LocalDateTime.now());

        this.commentRepository.save(newComment);
    }
}
