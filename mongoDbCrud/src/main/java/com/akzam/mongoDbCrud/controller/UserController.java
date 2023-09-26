package com.akzam.mongoDbCrud.controller;

import com.akzam.mongoDbCrud.annotation.ValidObjectId;
import com.akzam.mongoDbCrud.annotation.ValidPhoneNumber;
import com.akzam.mongoDbCrud.dto.Filter;
import com.akzam.mongoDbCrud.dto.user.UserRequestDto;
import com.akzam.mongoDbCrud.dto.user.UserResponseDto;
import com.akzam.mongoDbCrud.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            @PathVariable @ValidObjectId String id
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
            @PathVariable @ValidObjectId String id,
            @RequestBody @Valid UserRequestDto userRequestDto
    ) {
        return userService.update(id, userRequestDto);
    }

    @DeleteMapping("/id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(
            @PathVariable @ValidObjectId String id
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
