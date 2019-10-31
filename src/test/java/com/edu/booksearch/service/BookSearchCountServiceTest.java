package com.edu.booksearch.service;

import com.edu.booksearch.model.search.history.BookSearchCountResponseDto;
import com.edu.booksearch.persistent.h2.entity.SearchCountEntity;
import com.edu.booksearch.persistent.h2.repository.SearchCountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookSearchCountServiceTest {
    @Autowired
    private SearchCountRepository searchCountRepository;
    @Autowired
    private BookSearchCountService bookSearchCountService;

    @Test
    @Transactional
    void 인기_검색어_상위_10개_조회() {
        List<SearchCountEntity> searchCountEntities = new ArrayList<>();

        for (int i = 0; i < 3; ++i) {
            SearchCountEntity searchCountEntity = new SearchCountEntity();
            searchCountEntity.setKeyword("미움받을 용기" + i);
            searchCountEntity.setCount(10L * i);
            searchCountEntities.add(searchCountEntity);
        }
        searchCountRepository.saveAll(searchCountEntities);

        BookSearchCountResponseDto bookSearchCountResponseDto1 = new BookSearchCountResponseDto();
        bookSearchCountResponseDto1.setKeyword("미움받을 용기" + 2);
        bookSearchCountResponseDto1.setCount(20L);
        BookSearchCountResponseDto bookSearchCountResponseDto2 = new BookSearchCountResponseDto();
        bookSearchCountResponseDto2.setKeyword("미움받을 용기" + 1);
        bookSearchCountResponseDto2.setCount(10L);
        BookSearchCountResponseDto bookSearchCountResponseDto3 = new BookSearchCountResponseDto();
        bookSearchCountResponseDto3.setKeyword("미움받을 용기" + 0);
        bookSearchCountResponseDto3.setCount(0L);

        List<BookSearchCountResponseDto> expectedResult = Arrays.asList(
                bookSearchCountResponseDto1, bookSearchCountResponseDto2, bookSearchCountResponseDto3
        );
        List<BookSearchCountResponseDto> actualResult = bookSearchCountService.getSearchHistoryTop10();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void 아무_검색_내용이_없을_경우() {
        List<BookSearchCountResponseDto> expectedResult = new ArrayList<> ();
        List<BookSearchCountResponseDto> actualResult = bookSearchCountService.getSearchHistoryTop10();
        assertEquals(expectedResult, actualResult);
    }
}