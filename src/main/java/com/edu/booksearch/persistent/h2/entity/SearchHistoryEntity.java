package com.edu.booksearch.persistent.h2.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "search_history")
public class SearchHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no", nullable = false)
    private UserEntity userEntity;

    @Column(name = "keyword", nullable = false)
    private String keyword;

    @Column(name = "search_datetime", nullable = false)
    private LocalDateTime searchDateTime;
}
