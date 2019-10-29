package com.edu.booksearch.external;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KakaoBookSearchApiClientTest {

    @Autowired
    private KakaoBookSearchApiClient kakaoBookSearchApiClient;

    @BeforeEach
    void setUp() {
    }

    @Test
    void 특정_키워드_책_정보_가져오기() {
        kakaoBookSearchApiClient.findByQueryWithPage("미움받을 용기", 1).getDocuments().forEach(System.out::println);
    }
}