package com.edu.booksearch.service;

import com.edu.booksearch.external.KakaoBookSearchApiClient;
import com.edu.booksearch.model.search.*;
import com.edu.booksearch.model.search.kakao.KakaoApiResponseDto;
import com.edu.booksearch.persistent.h2.entity.SearchCountEntity;
import com.edu.booksearch.persistent.h2.entity.SearchHistoryEntity;
import com.edu.booksearch.persistent.h2.entity.UserEntity;
import com.edu.booksearch.persistent.h2.repository.SearchCountRepository;
import com.edu.booksearch.persistent.h2.repository.SearchHistoryRepository;
import com.edu.booksearch.persistent.h2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookSearchService {
    @Autowired
    private KakaoBookSearchApiClient kakaoBookSearchApiClient;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SearchHistoryRepository searchHistoryRepository;
    @Autowired
    private SearchCountRepository searchCountRepository;

    public BookSearchResponseDto searchBook(BookSearchRequestDto bookSearchRequestDto) {
        return makeBookSearchResponseDto(bookSearchRequestDto);
    }

    @Transactional
    public BookSearchResponseDto searchBook(BookSearchRequestDto bookSearchRequestDto, long userNo) {
        BookSearchResponseDto bookSearchResponseDto = makeBookSearchResponseDto(bookSearchRequestDto);
        Optional<UserEntity> userEntity = userRepository.findById(userNo);

        if (!userEntity.isPresent()) {
            throw new RuntimeException();
        }

        recordSearchHistory(userNo, bookSearchRequestDto.getQuery(), userEntity.get());
        recordSearchCount(bookSearchRequestDto.getQuery());

        return bookSearchResponseDto;
    }

    private void recordSearchHistory(long userNo, String query, UserEntity userEntity) {
        SearchHistoryEntity searchHistoryEntity = new SearchHistoryEntity();
        searchHistoryEntity.setUserNo(userNo);
        searchHistoryEntity.setKeyword(query);
        searchHistoryEntity.setSearchDateTime(LocalDateTime.now());
        userEntity.getSearchHistoryEntities().add(searchHistoryEntity);
        userRepository.saveAndFlush(userEntity);
    }

    private void recordSearchCount(String query) {
        Optional<SearchCountEntity> savedSearchCountEntity = searchCountRepository.findById(query);

        if (!savedSearchCountEntity.isPresent()) {
            SearchCountEntity searchCountEntity = new SearchCountEntity();
            searchCountEntity.setKeyword(query);
            searchCountRepository.save(searchCountEntity);
            return;
        }
        savedSearchCountEntity.get().setCount(savedSearchCountEntity.get().getCount() + 1);
        searchCountRepository.save(savedSearchCountEntity.get());
    }

    private BookSearchResponseDto makeBookSearchResponseDto(BookSearchRequestDto bookSearchRequestDto) {
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