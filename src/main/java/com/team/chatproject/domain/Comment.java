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
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String comments;

    @ManyToOne
    private Article article;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;
}
