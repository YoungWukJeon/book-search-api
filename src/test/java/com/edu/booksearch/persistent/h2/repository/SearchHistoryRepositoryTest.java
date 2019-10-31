package com.edu.booksearch.persistent.h2.repository;

import com.edu.booksearch.persistent.h2.entity.SearchHistoryEntity;
import com.edu.booksearch.persistent.h2.entity.UserEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Transactional
class SearchHistoryRepositoryTest {
    @Autowired
    private SearchHistoryRepository searchHistoryRepository;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        UserEntity userEntity1 = new UserEntity();
        userEntity1.setId("testUser1");
        userEntity1.setPassword("testPassword");
        UserEntity userEntity2 = new UserEntity();
        userEntity2.setId("testUser2");
        userEntity2.setPassword("testPassword");
        userRepository.saveAll(Arrays.asList(userEntity1, userEntity2));


        SearchHistoryEntity searchHistoryEntity = new SearchHistoryEntity();
        searchHistoryEntity.setKeyword("미움받을 용기");
        searchHistoryEntity.setSearchDateTime(LocalDateTime.now());
        searchHistoryEntity.setUserNo(1L);
        searchHistoryRepository.save(searchHistoryEntity);
    }

    @Test
    void 특정_사용자_검색_내용_조회() {
        searchHistoryRepository.findByUserNoOrderBySearchDateTimeDesc(1L).forEach(System.out::println);
    }

    @Test
    void 검색_기록이_없는_사용자의_검색_기록_조회() {
        List<SearchHistoryEntity> expectedResult = new ArrayList<> ();
        List<SearchHistoryEntity> actualResult = searchHistoryRepository.findByUserNoOrderBySearchDateTimeDesc(2L);
        assertEquals(expectedResult, actualResult);
    }
}