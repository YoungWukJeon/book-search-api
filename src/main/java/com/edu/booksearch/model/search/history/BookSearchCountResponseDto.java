package com.edu.booksearch.model.search.history;

import lombok.Data;

@Data
public class BookSearchCountResponseDto {
    String keyword;
    String count;

    public void setCount(long count) {
        this.count = Long.toString(count);
    }
}
