package com.edu.booksearch.service;

import com.edu.booksearch.model.RegistrationResponseDto;
import com.edu.booksearch.persistent.h2.entity.UserEntity;
import com.edu.booksearch.persistent.h2.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Test
    @Transactional
    void 회원가입_성공() {
        String id = "testUser";
        String password = "testPass";

        RegistrationResponseDto expectedResult = new RegistrationResponseDto();
        expectedResult.setId("1");
        expectedResult.setCode(200);
        expectedResult.setMessage("RegistrationSuccess");
        RegistrationResponseDto actualResult = userService.createUser(id, password);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    @Transactional
    void 중복된_아이디() {
        String id = "testUser";
        String password = "testPass";

        UserEntity userEntity = new UserEntity();
        userEntity.setId("testUser");
        userEntity.setPassword("testPass");
        userRepository.saveAndFlush(userEntity);

        RegistrationResponseDto expectedResult = new RegistrationResponseDto();
        expectedResult.setId("1");
        expectedResult.setCode(201);
        expectedResult.setMessage("IdExists");
        RegistrationResponseDto actualResult = userService.createUser(id, password);
        assertEquals(expectedResult, actualResult);
    }
}