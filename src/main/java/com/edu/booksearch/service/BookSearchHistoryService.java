package com.edu.booksearch.service;

import com.edu.booksearch.model.search.BookSearchHistoryResponseDto;
import com.edu.booksearch.persistent.h2.entity.SearchHistoryEntity;
import com.edu.booksearch.persistent.h2.entity.UserEntity;
import com.edu.booksearch.persistent.h2.repository.SearchHistoryRepository;
import com.edu.booksearch.persistent.h2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookSearchHistoryService {
    @Autowired
    private UserRepository userRepository;

    public List<BookSearchHistoryResponseDto> getSearchHistory(long userNo) {
        Optional<UserEntity> userEntity = userRepository.findById(userNo);

        if (!userEntity.isPresent()) {
            throw new RuntimeException();
        }

        return userEntity.get().getSearchHistoryEntities().stream()
                .map(entity -> {
                    BookSearchHistoryResponseDto bookSearchHistoryResponseDto = new BookSearchHistoryResponseDto();
                    bookSearchHistoryResponseDto.setKeyword(entity.getKeyword());
                    bookSearchHistoryResponseDto.setSearchDatetime(entity.getSearchDateTime());
                    return bookSearchHistoryResponseDto;
                }).sorted((o1, o2) -> -o1.getSearchDatetime().compareTo(o2.getSearchDatetime())).collect(Collectors.toList());
    }
}
