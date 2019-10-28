package com.edu.booksearch.persistent.h2.repository;

import com.edu.booksearch.persistent.h2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
