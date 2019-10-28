package com.edu.booksearch.persistent.h2.repository;

import com.edu.booksearch.persistent.h2.entity.UserEntity;
import com.edu.booksearch.util.PasswordEncoding;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    private PasswordEncoding passwordEncoding = new PasswordEncoding();

    @BeforeEach
    void setUp() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId("testUser");
        userEntity.setPassword(passwordEncoding.encode("testPassword"));
        userRepository.save(userEntity);
    }

    @Test
    void 모든_데이터_조회() {
        List<UserEntity> userEntities = userRepository.findAll();
        userEntities.forEach(System.out::println);
    }

    @Test
    void 특정_아이디_조회() {
        String id = "testUser";
        Optional<UserEntity> userEntity = userRepository.findOneById(id);
        System.out.println(userEntity.get());
    }
}