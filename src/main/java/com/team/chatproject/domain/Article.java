package com.team.chatproject.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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


    private long memberId;

    private  int viewCount;

    @DateTimeFormat(pattern = "yyyy/mm/dd")
    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    private List<Comment> commentList;


}
