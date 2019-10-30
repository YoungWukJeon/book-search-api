package com.edu.booksearch.service;

import com.edu.booksearch.model.search.*;
import com.edu.booksearch.model.search.kakao.KakaoApiResponseDto;
import com.edu.booksearch.model.search.kakao.KakaoBookInfoDto;
import com.edu.booksearch.model.search.kakao.KakaoBookMetaDto;
import com.edu.booksearch.util.DateTimeUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookSearchServiceTest {

    private KakaoApiResponseDto kakaoApiResponseDto;
    private DateTimeUtil dateTimeUtil = new DateTimeUtil();

    @Autowired
    private BookSearchService bookSearchService;


    @BeforeEach
    void setUp() {
        kakaoApiResponseDto = new KakaoApiResponseDto();
        KakaoBookMetaDto kakaoBookMetaDto = new KakaoBookMetaDto();
        List<KakaoBookInfoDto> kakaoBookInfoDtos = new ArrayList<> ();

        kakaoBookMetaDto.setTotalCount(1000);
        kakaoBookMetaDto.setPageableCount(997);
        kakaoBookMetaDto.setEnd(false);

        KakaoBookInfoDto kakaoBookInfoDto1 = new KakaoBookInfoDto();
        kakaoBookInfoDto1.setTitle("testTitle");
        kakaoBookInfoDto1.setThumbnail("test.thumbnail.com");
        kakaoBookInfoDto1.setAuthors(new ArrayList<> ());
        kakaoBookInfoDto1.getAuthors().add("testAuthor1");
        kakaoBookInfoDto1.getAuthors().add("testAuthor2");
        kakaoBookInfoDto1.setContents("testContent1");
        kakaoBookInfoDto1.setDatetime("2014-11-17T00:00:00.000+09:00");
        kakaoBookInfoDto1.setIsbn("8996991341 9788996991342");
        kakaoBookInfoDto1.setPublisher("testPublisher");
        kakaoBookInfoDto1.setPrice(13500);
        kakaoBookInfoDto1.setSalePrice(10900);
        kakaoBookInfoDtos.add(kakaoBookInfoDto1);

        kakaoApiResponseDto.setMeta(kakaoBookMetaDto);
        kakaoApiResponseDto.setDocuments(kakaoBookInfoDtos);
    }

    @Test
    void 카카오_메타_추출() {
        SearchMetaDto expectedResult = new SearchMetaDto();
        expectedResult.setTotalDoc(1000);
        expectedResult.setEnd(false);
        SearchMetaDto actualResult = ReflectionTestUtils.invokeMethod(bookSearchService, "extractSearchMetaDto", kakaoApiResponseDto);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void 카카오_문서_추출() {
        List<SearchResultDto> expectedResult = new ArrayList<> ();
        SearchResultDto searchResultDto = new SearchResultDto();
        searchResultDto.setTitle("testTitle");
        searchResultDto.setThumbnail("test.thumbnail.com");
        searchResultDto.setAuthor("testAuthor1, testAuthor2");
        searchResultDto.setContent("testContent1");
        searchResultDto.setPublishingDate(dateTimeUtil.kakaoDateParser("2014-11-17T00:00:00.000+09:00"));
        searchResultDto.setIsbn("8996991341 9788996991342");
        searchResultDto.setPublisher("testPublisher");
        searchResultDto.setPrice(13500);
        searchResultDto.setSalePrice(10900);
        expectedResult.add(searchResultDto);
        List<SearchResultDto> actualResult = ReflectionTestUtils.invokeMethod(bookSearchService, "extractSearchResults", kakaoApiResponseDto);

        assertEquals(expectedResult, actualResult);
    }
}