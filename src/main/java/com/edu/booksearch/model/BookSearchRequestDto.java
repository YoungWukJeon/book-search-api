package com.edu.booksearch.model;

import lombok.Data;

@Data
public class BookSearchRequestDto {
    private String query;
    private int page = 1;
}
