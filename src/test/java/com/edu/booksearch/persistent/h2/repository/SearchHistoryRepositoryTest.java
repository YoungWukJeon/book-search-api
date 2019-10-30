package com.edu.booksearch.persistent.h2.repository;

import com.edu.booksearch.persistent.h2.entity.SearchHistoryEntity;
import com.edu.booksearch.persistent.h2.entity.UserEntity;
import com.edu.booksearch.util.PasswordEncoding;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@SpringBootTest
class SearchHistoryRepositoryTest {

    @Autowired
    private SearchHistoryRepository searchHistoryRepository;
    @Autowired
    private UserRepository userRepository;
    private PasswordEncoding passwordEncoding = new PasswordEncoding();
    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        SearchHistoryEntity searchHistoryEntity = new SearchHistoryEntity();
        userEntity = new UserEntity();
        userEntity.setId("testUser");
        userEntity.setPassword(passwordEncoding.encode("testPassword"));
        userRepository.save(userEntity);

        searchHistoryEntity.setId(2);
        searchHistoryEntity.setKeyword("미움받을 용기");
        searchHistoryEntity.setSearchDateTime(LocalDateTime.now());
        searchHistoryEntity.setUserEntity(userEntity);
        searchHistoryRepository.save(searchHistoryEntity);
    }

    @Test
    @Transactional
    void 모든_검색_내용_조회() {
        searchHistoryRepository.findAll().forEach(System.out::println);
    }

    @Test
    @Transactional
    void 특정_사용자_검색_내용_조회() {
        searchHistoryRepository.findByUserEntity(userEntity).forEach(System.out::println);
    }
}