package com.team.chatproject.repository;

import com.team.chatproject.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository <Article,Long>{
}
