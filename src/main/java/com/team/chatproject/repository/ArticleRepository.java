package com.team.chatproject.repository;

import com.team.chatproject.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    ArrayList<Article> Articles =  new ArrayList<>();
//    @Override
//    ArrayList<Article> findAll();


    @Override
    Page<Article> findAll(Pageable pageable);
}

