package com.edu.booksearch.service;

import com.edu.booksearch.model.search.history.BookSearchCountResponseDto;
import com.edu.booksearch.persistent.h2.repository.SearchCountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookSearchCountService {
    @Autowired
    private SearchCountRepository searchCountRepository;

    public List<BookSearchCountResponseDto> getSearchHistoryTop10() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "count"));
        return searchCountRepository.findAll(pageable).stream()
                .map(p -> {
                    BookSearchCountResponseDto bookSearchCountResponseDto = new BookSearchCountResponseDto();
                    bookSearchCountResponseDto.setKeyword(p.getKeyword());
                    bookSearchCountResponseDto.setCount(p.getCount());
                    return bookSearchCountResponseDto;
                }).collect(Collectors.toList());
    }
}
