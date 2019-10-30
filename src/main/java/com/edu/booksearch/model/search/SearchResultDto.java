package com.edu.booksearch.model.search;

import com.edu.booksearch.util.DateTimeUtil;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public void setAuthor(List<String> authors) {
        this.author = authors.stream().collect(Collectors.joining(", "));
    }

    public void setPublishingDate(String datetime) {
        this.publishingDate = DateTimeUtil.kakaoDateParser(datetime);
    }
}
