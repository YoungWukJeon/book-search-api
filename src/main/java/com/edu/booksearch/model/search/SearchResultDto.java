package com.edu.booksearch.model.search;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SearchResultDto {
    private String title;
    private String thumbnail;
    private String content;
    private String isbn;
    private String author;
    private String publisher;
    private LocalDateTime publishingDate;
    private int price;
    private int salePrice;
}
