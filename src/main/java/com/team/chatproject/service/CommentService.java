package com.team.chatproject.service;

import com.team.chatproject.domain.Article;
import com.team.chatproject.domain.Comment;
import com.team.chatproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    public Comment getComment(long commentId) {
        Optional<Comment> OpComment = commentRepository.findById(commentId);
        Comment comment = OpComment.get();
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
    @Transactional

    // 수정
    public void update(Long id, String comments) {
        Optional<Comment> opComment = commentRepository.findById(id);
        Comment comment = opComment.get();
        comment.setUpdateDate(LocalDateTime.now());
        comment.setComments(comments);

        this.commentRepository.save(comment);
    }
    @Transactional
    public void delete(Comment comment) {
        this.commentRepository.delete(comment);
    }
}
