package com.edu.booksearch.service;

import com.edu.booksearch.model.LoginRequestDto;
import com.edu.booksearch.model.LoginResponseDto;
import com.edu.booksearch.persistent.h2.entity.UserEntity;
import com.edu.booksearch.persistent.h2.repository.UserRepository;
import com.edu.booksearch.util.PasswordEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;
    private PasswordEncoding passwordEncoding = new PasswordEncoding();

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Optional<UserEntity> userEntity = userRepository.findOneById(loginRequestDto.getId());
        LoginResponseDto loginResponseDto = new LoginResponseDto();

        loginResponseDto.setId("1");

        if (!userEntity.isPresent()) {
            loginResponseDto.setCode(101);
            loginResponseDto.setMessage("InvalidId");
        } else if (!passwordEncoding.matches(loginRequestDto.getPassword(), userEntity.get().getPassword())) {
            loginResponseDto.setCode(102);
            loginResponseDto.setMessage("InvalidPassword");
        } else {
            loginResponseDto.setCode(100);
            loginResponseDto.setMessage("LoginSuccess");
            loginResponseDto.setUserEntity(userEntity.get());
        }

        return loginResponseDto;
    }
}
