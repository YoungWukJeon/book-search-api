package com.edu.booksearch.persistent.h2.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private long userNo;

    @Column(name = "id", nullable = false, unique = true, length = 30)
    private String id;

    @Column(name = "password", nullable = false)
    private String password;
}
