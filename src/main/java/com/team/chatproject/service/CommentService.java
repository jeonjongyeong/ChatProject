package com.team.chatproject.service;

import com.team.chatproject.domain.Article;
import com.team.chatproject.domain.Comment;
import com.team.chatproject.domain.Member;
import com.team.chatproject.domain.Rq;
import com.team.chatproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    MemberService memberService;

    @Autowired
    Rq rq;

    public Comment getComment(long commentId) {
        Optional<Comment> OpComment = commentRepository.findById(commentId);
        Comment comment = OpComment.get();
        return comment;
    }

    public void create(Article article, String comments) {
        Comment newComment = new Comment();
        newComment.setComments(comments);
        newComment.setArticle(article);
        Member member = memberService.getMemberById(rq.getLoginMemberId());
        newComment.setNickname(member.getNickName());
        newComment.setRegDate(LocalDate.now());
        newComment.setUpdateDate(LocalDate.now());

        this.commentRepository.save(newComment);
    }
    @Transactional

    // 수정
    public void update(Long id, String comments) {
        Optional<Comment> opComment = commentRepository.findById(id);
        Comment comment = opComment.get();
        comment.setUpdateDate(LocalDate.now());
        comment.setComments(comments);

        this.commentRepository.save(comment);
    }
    @Transactional
    public void delete(Comment comment) {
        this.commentRepository.delete(comment);
    }
}
