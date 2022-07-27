package com.team.chatproject.servise;

import com.team.chatproject.domain.Article;
import com.team.chatproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ArticleServise {
    @Autowired
    ArticleRepository articleRepository;

    public void create(long memberId, String title, String body) {
        Article article = new Article();
        article.setMemberId(memberId);
        article.setTitle(title);
        article.setBody(body);
        article.setRegDate(LocalDateTime.now());
        article.setUpdateDate(LocalDateTime.now());

        articleRepository.save(article);
    }
}
