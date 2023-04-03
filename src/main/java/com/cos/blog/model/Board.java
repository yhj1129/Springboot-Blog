package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity //User 클래스가 자동으로 MySql에 테이블로 생성됨
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob //대용량 데이터를 저장할 때
    private String content;

    private int count;

    @CreationTimestamp
    private Timestamp createDate;

    @ManyToOne//Many = Board, User = One 한 명의 유저는 많은 게시글을 쓸 수 있다 //User 와의 연관 관계
    @JoinColumn(name = "userId")
    private User user; //DB는 오브젝트를 저장할 수 없다. FK를 이용한다 . 자바는 오브젝트를 저장할 수 있다.  ORM을 이용하면 DB에서도 저장할 수 있다

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
     //mappedBy 이면 연관관계의 주인이 아니다. 나는 FK가 아니다. DB에 col을 만들지 않겠다
    //replyId 필요 없음
    private List<Reply> reply;
}
