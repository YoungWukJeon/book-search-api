package com.edu.booksearch.model;

import com.edu.booksearch.persistent.h2.entity.UserEntity;
import lombok.Data;

@Data
public class LoginResponseDto {
    private String id;
    private int code;
    private UserEntity userEntity;
    private String message;
}
