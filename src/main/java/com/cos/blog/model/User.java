package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@DynamicInsert //insert 시에 null인 필드 제외해줌
@Entity //User 클래스가 자동으로 MySql에 테이블로 생성됨
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에 연결된 DB의 넘버링 전략을 따라감 mysql이면 auto-Increment
    private int id;

    @Column(nullable = false, length = 30)
    private String username;
    @Column(nullable = false, length = 100)
    private String password;
    @Column(nullable = false, length = 50)
    private String email;

//    @ColumnDefault("'user'")
    @Enumerated(EnumType.STRING)
    private RoleType role;//Enum을 쓰는게 좋다 -> 회원가입을 했으면 admin, user 등 권한을 다르게 줄 때

    @CreationTimestamp //시간 자동 입력됨
    private Timestamp createDate;

}
