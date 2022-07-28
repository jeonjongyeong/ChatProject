package com.team.chatproject.service;

import com.team.chatproject.domain.Article;
import com.team.chatproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public void create(long memberId, String title, String body) {
        Article article = new Article();
//        article.setMemberId(memberId);
        article.setTitle(title);
        article.setBody(body);
//        article.setRegDate(LocalDateTime.now());
//        article.setUpdateDate(LocalDateTime.now());

        articleRepository.save(article);
    }


    public List<Article> getList() {
        List<Article> articles = articleRepository.findAll();
        return articles;
    }

    public Article getDetail(Long id) {
       return articleRepository.findById(id).orElse(null);
    }

}