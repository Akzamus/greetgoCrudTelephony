package com.akzam.postgreSqlCrud.service;

import com.akzam.postgreSqlCrud.dto.Filter;
import com.akzam.postgreSqlCrud.dto.user.UserRequestDto;
import com.akzam.postgreSqlCrud.dto.user.UserResponseDto;
import com.akzam.postgreSqlCrud.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<UserResponseDto> getAll(Filter filter);
    UserResponseDto getById(UUID id);
    UserResponseDto getByPhoneNumber(String phoneNumber);
    UserResponseDto create(UserRequestDto requestDto);
    UserResponseDto update(UUID id, UserRequestDto requestDto);
    void deleteById(UUID id);
    void deleteByPhoneNumber(String phoneNumber);
    User getUserById(UUID id);
    User getUserByPhoneNumber(String phoneNumber);

}
