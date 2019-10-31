package com.edu.booksearch.controller;

import com.edu.booksearch.model.LoginRequestDto;
import com.edu.booksearch.model.RegistrationResponseDto;
import com.edu.booksearch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserApiController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "")
    public RegistrationResponseDto createUser(@RequestBody LoginRequestDto loginRequestDto) {
        return userService.createUser(loginRequestDto.getId(), loginRequestDto.getPassword());
    }
}
