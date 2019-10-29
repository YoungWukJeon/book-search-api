package com.edu.booksearch.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class BookInfoDto {

    @JsonProperty(value = "title")
    private String title;   // 제목

    @JsonProperty(value = "thumbnail")
    private String thumbnail;   // 도서 썸네일

    @JsonProperty(value = "contents")
    private String contents;    // 소개

    @JsonProperty(value = "isbn")
    private String isbn;    // ISBN

    @JsonProperty(value = "authors")
    private List<String> authors;   // 저자

    @JsonProperty(value = "publisher")
    private String publisher;   // 출판사

    @JsonProperty(value = "datetime")
    private String datetime;    // 출판일

    @JsonProperty(value = "price")
    private int price;  // 정가

    @JsonProperty(value = "sale_price")
    private int salePrice;  // 판매가
}
