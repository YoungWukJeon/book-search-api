package com.edu.booksearch.controller;

import com.edu.booksearch.model.LoginRequestDto;
import com.edu.booksearch.model.LoginResponseDto;
import com.edu.booksearch.persistent.h2.entity.UserEntity;
import com.edu.booksearch.persistent.h2.repository.UserRepository;
import com.edu.booksearch.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/login")
public class LoginApiController {

    @Autowired
    private LoginService loginService;

    @PostMapping(value = "")
    public LoginResponseDto login(@RequestParam LoginRequestDto loginRequestDto) {
        return loginService.login(loginRequestDto);
    }
}
