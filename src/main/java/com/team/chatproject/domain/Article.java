package com.team.chatproject.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Getter
@Setter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String title ;

    @Column
    private String body ;

    private long memberId;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;

}
