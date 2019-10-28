package com.edu.booksearch.persistent.h2.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no", columnDefinition = "사용자_번호")
    private long userNo;

    @Column(name = "id", nullable = false, unique = true, length = 30, columnDefinition = "사용자_로그인_아이디")
    private String id;

    @Column(name = "password", nullable = false, columnDefinition = "사용자_로그인_비밀번호")
    private String password;
}
