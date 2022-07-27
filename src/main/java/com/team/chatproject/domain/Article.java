package com.team.chatproject.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String title ;

    @Column
    private String body ;

//    private long memberId;

//    private LocalDateTime regDate;
//    private LocalDateTime updateDate;

}
