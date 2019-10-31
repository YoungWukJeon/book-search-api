package com.edu.booksearch.service;

import com.edu.booksearch.model.search.history.BookSearchHistoryResponseDto;
import com.edu.booksearch.persistent.h2.entity.SearchHistoryEntity;
import com.edu.booksearch.persistent.h2.entity.UserEntity;
import com.edu.booksearch.persistent.h2.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Transactional
@SpringBootTest
class BookSearchHistoryServiceTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookSearchHistoryService bookSearchHistoryService;

    @Test
    void 특정_사용자_검색_기록_조회() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId("testUser");
        userEntity.setPassword("testPassword");

        SearchHistoryEntity searchHistoryEntity1 = new SearchHistoryEntity();
        searchHistoryEntity1.setUserNo(1L);
        searchHistoryEntity1.setKeyword("테스트 키워드1");
        searchHistoryEntity1.setSearchDateTime(LocalDateTime.now());

        SearchHistoryEntity searchHistoryEntity2 = new SearchHistoryEntity();
        searchHistoryEntity2.setUserNo(1L);
        searchHistoryEntity2.setKeyword("테스트 키워드2");
        searchHistoryEntity2.setSearchDateTime(LocalDateTime.now().plusDays(15));

        userEntity.getSearchHistoryEntities().addAll(Arrays.asList(searchHistoryEntity1, searchHistoryEntity2));
        userRepository.save(userEntity);

        BookSearchHistoryResponseDto bookSearchHistoryResponseDto1 = new BookSearchHistoryResponseDto();
        bookSearchHistoryResponseDto1.setSearchDatetime(searchHistoryEntity1.getSearchDateTime());
        bookSearchHistoryResponseDto1.setKeyword("테스트 키워드1");
        BookSearchHistoryResponseDto bookSearchHistoryResponseDto2 = new BookSearchHistoryResponseDto();
        bookSearchHistoryResponseDto2.setSearchDatetime(searchHistoryEntity2.getSearchDateTime());
        bookSearchHistoryResponseDto2.setKeyword("테스트 키워드2");

        List<BookSearchHistoryResponseDto> expectedResult = Arrays.asList(bookSearchHistoryResponseDto2, bookSearchHistoryResponseDto1);

        List<BookSearchHistoryResponseDto> actualResult = bookSearchHistoryService.getSearchHistory(1L);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void 없는_사용자에_대한_검색_기록_조회() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            bookSearchHistoryService.getSearchHistory(100L);
        });
    }

    @Test
    void 검색_기록이_없는_사용자의_검색_기록_조회() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId("testUser");
        userEntity.setPassword("testPassword");
        userRepository.save(userEntity);

        List<BookSearchHistoryResponseDto> expectedResult = new ArrayList<> ();
        List<BookSearchHistoryResponseDto> actualResult = bookSearchHistoryService.getSearchHistory(1L);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void LocalDateTime_정렬() {
        LocalDateTime localDateTime1 = LocalDateTime.parse("2019-07-15 16:41:53", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime localDateTime2 = LocalDateTime.parse("2019-07-16 16:41:53", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime localDateTime3 = LocalDateTime.parse("2019-07-15 16:41:54", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime localDateTime4 = LocalDateTime.parse("2019-07-16 16:41:53", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        List<LocalDateTime> list = Arrays.asList(localDateTime1, localDateTime2, localDateTime3, localDateTime4);
        System.out.println("**before sorting**");
        list.forEach(System.out::println);
        System.out.println("**after sorting**");
        list.stream().sorted((o1, o2) -> -o1.compareTo(o2)).forEach(System.out::println);

        assertEquals(-1, localDateTime1.compareTo(localDateTime2));
        assertEquals(1, localDateTime3.compareTo(localDateTime1));
        assertEquals(0, localDateTime2.compareTo(localDateTime4));
    }
}