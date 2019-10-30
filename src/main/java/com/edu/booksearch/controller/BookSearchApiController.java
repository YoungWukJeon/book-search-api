package com.edu.booksearch.controller;

import com.edu.booksearch.model.search.BookSearchRequestDto;
import com.edu.booksearch.model.search.BookSearchResponseDto;
import com.edu.booksearch.service.BookSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/book")
public class BookSearchApiController {
    @Autowired
    private BookSearchService bookSearchService;

    @GetMapping("/search")
    public BookSearchResponseDto searchBook(@RequestParam BookSearchRequestDto bookSearchRequestDto) {
        return bookSearchService.searchBook(bookSearchRequestDto);
    }
}
