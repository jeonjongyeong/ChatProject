package com.team.chatproject.service;

import com.team.chatproject.domain.*;
import com.team.chatproject.repository.ArticleRepository;
import com.team.chatproject.util.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;


    @Autowired
    private Rq rq;

    @Autowired
    private MemberService memberService;


    public Page<Article> getPageList(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    public Boolean getPageCheck(Pageable pageable) {
        Page<Article> saved = getPageList(pageable);
        Boolean check = saved.hasNext();

        return check;
    }

//    public List<Article> getList() {
//        List<Article> articles = articleRepository.findAll();
//        return articles;
//    }


    public Article getDetail(Long id) {
        Optional<Article> opArticle = this.articleRepository.findById(id);
        if (opArticle.isPresent()) {
            Article article = opArticle.get();
            List<Comment> commentList = article.getCommentList();
            Collections.reverse(commentList);
            article.setCommentList(commentList);
            article.setViewCount(article.getViewCount() + 1);
            this.articleRepository.save(article);
            return article;
        } else {
            throw new DataNotFoundException("article not found");
        }
    }

    public void create(String title, String body) {
        Article article = new Article();
        //article.setMemberId(memberId);
        article.setTitle(title);
        article.setBody(body);
        Member member = memberService.getMemberById(rq.getLoginMemberId());
        article.setNickname(member.getNickName());
        article.setRegDate(LocalDate.now());
        article.setUpdateDate(LocalDate.now());
        this.articleRepository.save(article);
    }

    public void delete(Article article) {
        this.articleRepository.delete(article);
    }


    public void modify(Long id, String title, String body) {
        Optional<Article> opArticle = articleRepository.findById(id);
        Article article = opArticle.get();
        article.setUpdateDate(LocalDate.now());
        article.setTitle(title);
        article.setBody(body);


        this.articleRepository.save(article);

    }

    public ResultData actorCanModify(int actorId, Article article) {
        if (article == null) {
            return ResultData.from("F-1", "???????????? ???????????? ????????????.");
        }

        if (article.getMemberId() != actorId) {
            return ResultData.from("F-2", "????????? ????????????.");
        }

        return ResultData.from("S-1", "????????? ????????? ???????????????.");
    }
}

