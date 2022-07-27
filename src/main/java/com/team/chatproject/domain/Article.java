package com.team.chatproject.domain;

import lombok.Data;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
public class Article {
    @Id
    private long id;

    private String title ;
    private String body ;

    private long memberId;

    private String boardId;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;

}
