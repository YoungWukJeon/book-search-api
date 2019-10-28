package com.edu.booksearch.service;

import com.edu.booksearch.model.LoginRequestDto;
import com.edu.booksearch.model.LoginResponseDto;
import com.edu.booksearch.persistent.h2.entity.UserEntity;
import com.edu.booksearch.persistent.h2.repository.UserRepository;
import com.edu.booksearch.util.PasswordEncoding;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginServiceTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginService loginService;
    private PasswordEncoding passwordEncoding = new PasswordEncoding();

    @BeforeEach
    void setUp() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId("testUser");
        userEntity.setPassword(passwordEncoding.encode("testPassword"));
        userRepository.save(userEntity);
    }

    @Test
    void 로그인_성공() {
        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setId("testUser");
        loginRequestDto.setPassword("testPassword");

        UserEntity userEntity = userRepository.findOneById(loginRequestDto.getId()).get();

        LoginResponseDto expectedLoginResponseDto = new LoginResponseDto();
        expectedLoginResponseDto.setId("1");
        expectedLoginResponseDto.setCode(100);
        expectedLoginResponseDto.setMessage("LoginSuccess");
        expectedLoginResponseDto.setUserEntity(userEntity);

        LoginResponseDto actualLoginResponseDto = loginService.login(loginRequestDto);

        assertEquals(expectedLoginResponseDto, actualLoginResponseDto);
    }

    @Test
    void 로그인_실패_없는_아이디() {
        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setId("testUser1");
        loginRequestDto.setPassword("testPassword");

        LoginResponseDto expectedLoginResponseDto = new LoginResponseDto();
        expectedLoginResponseDto.setId("1");
        expectedLoginResponseDto.setCode(101);
        expectedLoginResponseDto.setMessage("InvalidId");

        LoginResponseDto actualLoginResponseDto = loginService.login(loginRequestDto);

        assertEquals(expectedLoginResponseDto, actualLoginResponseDto);
    }

    @Test
    void 로그인_실패_비밀번호_다름() {
        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setId("testUser");
        loginRequestDto.setPassword("testPassword1");

        UserEntity userEntity = userRepository.findOneById(loginRequestDto.getId()).get();

        LoginResponseDto expectedLoginResponseDto = new LoginResponseDto();
        expectedLoginResponseDto.setId("1");
        expectedLoginResponseDto.setCode(102);
        expectedLoginResponseDto.setMessage("InvalidPassword");

        LoginResponseDto actualLoginResponseDto = loginService.login(loginRequestDto);

        assertEquals(expectedLoginResponseDto, actualLoginResponseDto);
    }
}