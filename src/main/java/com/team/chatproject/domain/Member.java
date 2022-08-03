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
    private int id;

    @Column(unique = true)
    private String loginId ;


    private String loginPw ;

    @Column
    private String name;

    @Column(unique = true)
    private String nickName;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    @Column
    private int authority;

    private int delStatus;
    private LocalDateTime delDate;


}
