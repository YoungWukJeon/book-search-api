package com.edu.booksearch.service;

import com.edu.booksearch.external.KakaoBookSearchApiClient;
import com.edu.booksearch.model.search.*;
import com.edu.booksearch.model.search.kakao.KakaoApiResponseDto;
import com.edu.booksearch.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookSearchService {
    @Autowired
    private KakaoBookSearchApiClient kakaoBookSearchApiClient;

    public BookSearchResponseDto searchBook(BookSearchRequestDto bookSearchRequestDto) {
        BookSearchResponseDto bookSearchResponseDto = new BookSearchResponseDto();
        KakaoApiResponseDto kakaoApiResponseDto = kakaoBookSearchApiClient.findByQueryWithPage(
                bookSearchRequestDto.getQuery(), bookSearchRequestDto.getPage()
        );
        bookSearchResponseDto.setSearchMetaInfo(extractSearchMetaDto(kakaoApiResponseDto));
        bookSearchResponseDto.setSearchResults(extractSearchResults(kakaoApiResponseDto));

        return bookSearchResponseDto;
    }

    private SearchMetaDto extractSearchMetaDto(KakaoApiResponseDto kakaoApiResponseDto) {
        SearchMetaDto searchMetaDto = new SearchMetaDto();
        searchMetaDto.setTotalDoc(kakaoApiResponseDto.getMeta().getTotalCount());
        searchMetaDto.setEnd(kakaoApiResponseDto.getMeta().isEnd());
        return searchMetaDto;
    }

    private List<SearchResultDto> extractSearchResults(KakaoApiResponseDto kakaoApiResponseDto) {
        return kakaoApiResponseDto.getDocuments().stream().map(doc -> {
            SearchResultDto searchResultDto = new SearchResultDto();
            searchResultDto.setTitle(doc.getTitle());
            searchResultDto.setThumbnail(doc.getThumbnail());
            searchResultDto.setContent(doc.getContents());
            searchResultDto.setIsbn(doc.getIsbn());
            searchResultDto.setAuthor(doc.getAuthors());
            searchResultDto.setPublisher(doc.getPublisher());
            searchResultDto.setPublishingDate(doc.getDatetime());
            searchResultDto.setPrice(doc.getPrice());
            searchResultDto.setSalePrice(doc.getSalePrice());
            return searchResultDto;
        }).collect(Collectors.toList());
    }
}