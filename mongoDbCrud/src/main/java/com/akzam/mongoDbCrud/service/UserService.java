package com.akzam.mongoDbCrud.service;

import com.akzam.mongoDbCrud.document.User;
import com.akzam.mongoDbCrud.dto.Filter;
import com.akzam.mongoDbCrud.dto.user.UserRequestDto;
import com.akzam.mongoDbCrud.dto.user.UserResponseDto;

import java.util.List;

public interface UserService {

    List<UserResponseDto> getAll(Filter filter);
    UserResponseDto getById(String id);
    UserResponseDto getByPhoneNumber(String phoneNumber);
    UserResponseDto create(UserRequestDto requestDto);
    UserResponseDto update(String id, UserRequestDto requestDto);
    void deleteById(String id);
    void deleteByPhoneNumber(String phoneNumber);
    User getUserById(String id);
    User getUserByPhoneNumber(String phoneNumber);

}
