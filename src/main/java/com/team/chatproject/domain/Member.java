package com.team.chatproject.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String loginId ;


    private String loginPw ;
    private String name ;
    private String nikName ;

    private LocalDateTime regDate ;
    private LocalDateTime updateDate ;

    private int authority;
    private int delStatus;
    private LocalDateTime delDate;

}
