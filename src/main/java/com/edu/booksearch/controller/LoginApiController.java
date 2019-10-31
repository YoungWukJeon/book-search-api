package com.edu.booksearch.controller;

import com.edu.booksearch.model.LoginRequestDto;
import com.edu.booksearch.model.LoginResponseDto;
import com.edu.booksearch.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/login")
public class LoginApiController {
    @Autowired
    private LoginService loginService;

    @PostMapping(value = "")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto) {
        return loginService.login(loginRequestDto);
    }
}
