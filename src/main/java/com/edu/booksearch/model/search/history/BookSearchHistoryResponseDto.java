package com.edu.booksearch.model.search.history;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookSearchHistoryResponseDto {
    String keyword;
    LocalDateTime searchDatetime;
}
