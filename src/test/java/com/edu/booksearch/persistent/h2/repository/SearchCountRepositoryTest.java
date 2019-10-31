package com.edu.booksearch.persistent.h2.repository;

import com.edu.booksearch.persistent.h2.entity.SearchCountEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class SearchCountRepositoryTest {

    @Autowired
    private SearchCountRepository searchCountRepository;

    @BeforeEach
    void setUp() {
        List<SearchCountEntity> searchCountEntities = new ArrayList<> ();

        for (int i = 0; i < 6; ++i) {
            SearchCountEntity searchCountEntity = new SearchCountEntity();
            searchCountEntity.setKeyword("미움받을 용기" + i);
            searchCountEntity.setCount(10L * i);
            searchCountEntities.add(searchCountEntity);
        }
        searchCountRepository.saveAll(searchCountEntities);
    }

    @Test
    void 모든_키워드_검색_횟수_출력() {
        searchCountRepository.findAll().forEach(System.out::println);
    }

    @Test
    void 인기순으로_5개_출력() {
        Sort sort = Sort.by(Sort.Direction.DESC, "count");
        Pageable pageable = PageRequest.of(0, 5, sort);
        searchCountRepository.findAll(pageable).forEach(System.out::println);
    }
}