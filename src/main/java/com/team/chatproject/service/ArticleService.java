package com.team.chatproject.service;

import com.team.chatproject.domain.Article;
import com.team.chatproject.repository.ArticleRepository;
import com.team.chatproject.util.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;


    public List<Article> getList() {
        List<Article> articles = articleRepository.findAll();
        return articles;
    }

    public Article getDetail(Long id) {
        Optional<Article> opArticle = this.articleRepository.findById(id);
        if (opArticle.isPresent()) {
            Article article = opArticle.get();
            article.setViewCount(article.getViewCount() + 1);
            this.articleRepository.save(article);
            return article;
        }else{
            throw new DataNotFoundException("article not found");
        }
    }

    public void create(String title, String body) {
        Article article = new Article();
        //article.setMemberId(memberId);
        article.setTitle(title);
        article.setBody(body);
        article.setRegDate(LocalDateTime.now());
        article.setUpdateDate(LocalDateTime.now());
        this.articleRepository.save(article);
    }
    public void delete(Article article) {
        this.articleRepository.delete(article);
    }

    public void test(Article article) {
        this.articleRepository.delete(article);
    }

    public void modify(Long id, String title, String body) {
        Optional<Article> opArticle = articleRepository.findById(id);
        Article article = opArticle.get();

        article.setUpdateDate(LocalDateTime.now());
        article.setTitle(title);
        article.setBody(body);

        this.articleRepository.save(article);

    }
}

