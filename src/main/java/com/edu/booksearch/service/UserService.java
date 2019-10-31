package com.edu.booksearch.service;

import com.edu.booksearch.model.RegistrationResponseDto;
import com.edu.booksearch.persistent.h2.entity.UserEntity;
import com.edu.booksearch.persistent.h2.repository.UserRepository;
import com.edu.booksearch.util.PasswordEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public RegistrationResponseDto createUser(String id, String password) {
        Optional<UserEntity> savedUserEntity = userRepository.findOneById(id);
        RegistrationResponseDto registrationResponseDto = new RegistrationResponseDto();
        registrationResponseDto.setId("1");

        if (savedUserEntity.isPresent()) {
            registrationResponseDto.setCode(201);
            registrationResponseDto.setMessage("IdExists");
        } else {
            UserEntity userEntity = new UserEntity();
            userEntity.setId(id);
            userEntity.setPassword(password);
            userRepository.save(userEntity);
            registrationResponseDto.setCode(200);
            registrationResponseDto.setMessage("RegistrationSuccess");
        }
        return registrationResponseDto;
    }
}
