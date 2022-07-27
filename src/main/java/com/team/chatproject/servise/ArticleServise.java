package com.team.chatproject.servise;

import com.team.chatproject.domain.Article;
import com.team.chatproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleServise {
    @Autowired
    private ArticleRepository articleRepository;


    public List<Article> getList() {
        List<Article> articles = articleRepository.findAll();
        return articles;
    }

    public Article getDetail(Long id) {
       return articleRepository.findById(id).orElse(null);
    }

    public void create(String title, String body) {
        Article article = new Article();
//        article.setMemberId(memberId);
        article.setTitle(title);
        article.setBody(body);
        article.setRegDate(LocalDateTime.now());
        article.setUpdateDate(LocalDateTime.now());
        this.articleRepository.save(article);
    }
}
