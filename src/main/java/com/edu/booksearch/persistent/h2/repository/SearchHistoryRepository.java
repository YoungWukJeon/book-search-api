package com.edu.booksearch.persistent.h2.repository;

import com.edu.booksearch.persistent.h2.entity.SearchHistoryEntity;
import com.edu.booksearch.persistent.h2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchHistoryRepository extends JpaRepository<SearchHistoryEntity, Long> {
    List<SearchHistoryEntity> findByUserEntityOrderBySearchDateTimeDesc(UserEntity userEntity);
}
