package com.edu.booksearch.persistent.h2.repository;

import com.edu.booksearch.persistent.h2.entity.UserEntity;
import com.edu.booksearch.util.PasswordEncoding;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @BeforeEach
    void setUp() {
        PasswordEncoding passwordEncoding = new PasswordEncoding();

        UserEntity userEntity = new UserEntity();
        userEntity.setId("testUser");
        userEntity.setPassword(passwordEncoding.encode("testPassword"));
        System.out.println(userEntity);
        userRepository.save(userEntity);
    }

    @Autowired
    private UserRepository userRepository;

    @Test
    void 모든_데이터_조회() {
        List<UserEntity> userEntities = userRepository.findAll();
        userEntities.forEach(System.out::println);
    }
}