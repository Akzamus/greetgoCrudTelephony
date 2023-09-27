package com.akzam.postgreSqlCrud.controller;

import com.akzam.postgreSqlCrud.annotation.ValidPhoneNumber;
import com.akzam.postgreSqlCrud.dto.Filter;
import com.akzam.postgreSqlCrud.dto.user.UserRequestDto;
import com.akzam.postgreSqlCrud.dto.user.UserResponseDto;
import com.akzam.postgreSqlCrud.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponseDto> getAllUsers(
            @Valid @RequestBody Filter filter
    ) {
        return userService.getAll(filter);
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto getUserById(
            @PathVariable UUID id
    ) {
        return userService.getById(id);
    }

    @GetMapping("/phone-number/{phoneNumber}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto getUserByPhoneNumber(
            @PathVariable @ValidPhoneNumber String phoneNumber
    ) {
        return userService.getByPhoneNumber(phoneNumber);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto createUser(
            @Valid @RequestBody UserRequestDto requestDto
    ) {
        return userService.create(requestDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto updateUser(
            @PathVariable UUID id,
            @RequestBody @Valid UserRequestDto userRequestDto
    ) {
        return userService.update(id, userRequestDto);
    }

    @DeleteMapping("/id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(
            @PathVariable UUID id
    ) {
        userService.deleteById(id);
    }

    @DeleteMapping("/phone-number/{phoneNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserByPhoneNumber(
            @PathVariable @ValidPhoneNumber String phoneNumber
    ) {
        userService.deleteByPhoneNumber(phoneNumber);
    }
}
