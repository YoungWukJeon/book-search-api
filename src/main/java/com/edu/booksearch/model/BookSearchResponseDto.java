package com.edu.booksearch.model;

import lombok.Data;

import java.util.List;

@Data
public class BookSearchResponseDto {
    private SearchMetaDto searchMetaInfo;
    private List<SearchResultDto> searchResults;
}
