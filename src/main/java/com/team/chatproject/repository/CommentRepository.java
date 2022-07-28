package com.team.chatproject.repository;

import com.team.chatproject.domain.Article;
import com.team.chatproject.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Override
    ArrayList<Comment> findAll();

    Comment findByArticleId(Long id);
}
