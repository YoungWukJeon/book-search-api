package com.edu.booksearch.persistent.h2.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "search_count")
public class SearchCountEntity {
    @Id
    @Column(name = "keyword")
    private String keyword;

    @Column(name = "count", nullable = false)
    private long count = 0;
}
