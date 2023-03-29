package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에 연결된 DB의 넘버링 전략을 따라감 mysql이면 auto-Increment
    private int id;

    @Column(nullable = false, length = 200)
    private String content;

    // 이 답변을 어느 게시글에?
    @ManyToOne //하나의 게시글에 여러 개의 답변을 달 수 있다
    @JoinColumn(name="boardId")
    private Board board;

    @ManyToOne //하나의 게시글에 여러 유저가 답변을 달 수 있다
    @JoinColumn(name = "userId")
    private User user;

    @CreationTimestamp
    private Timestamp createTime;
}
