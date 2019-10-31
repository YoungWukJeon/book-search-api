package com.edu.booksearch.persistent.h2.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(targetEntity = SearchHistoryEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private List<SearchHistoryEntity> searchHistoryEntities = new ArrayList<> ();
}
