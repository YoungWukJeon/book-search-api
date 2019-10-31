package com.edu.booksearch.controller;

import com.edu.booksearch.model.search.history.BookSearchCountResponseDto;
import com.edu.booksearch.model.search.history.BookSearchHistoryResponseDto;
import com.edu.booksearch.model.search.BookSearchRequestDto;
import com.edu.booksearch.model.search.BookSearchResponseDto;
import com.edu.booksearch.service.BookSearchCountService;
import com.edu.booksearch.service.BookSearchHistoryService;
import com.edu.booksearch.service.BookSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/v1/book")
public class BookSearchApiController {
    @Autowired
    private BookSearchService bookSearchService;
    @Autowired
    private BookSearchHistoryService bookSearchHistoryService;
    @Autowired
    private BookSearchCountService bookSearchCountService;

    @PostMapping("/search")
    public BookSearchResponseDto searchBook(@RequestParam BookSearchRequestDto bookSearchRequestDto) {
        return bookSearchService.searchBook(bookSearchRequestDto);
    }

    @PostMapping("/search")
    public BookSearchResponseDto searchBook(@RequestParam BookSearchRequestDto bookSearchRequestDto, @RequestBody long userNo) {
        return bookSearchService.searchBook(bookSearchRequestDto, userNo);
    }

    @PostMapping("/history/user")
    public List<BookSearchHistoryResponseDto> getSearchHistory(@RequestBody long userNo) {
        return bookSearchHistoryService.getSearchHistory(userNo);
    }

    @GetMapping("/history/top10")
    public List<BookSearchCountResponseDto> getSearchHistoryTop10() {
        return bookSearchCountService.getSearchHistoryTop10();
    }
}
