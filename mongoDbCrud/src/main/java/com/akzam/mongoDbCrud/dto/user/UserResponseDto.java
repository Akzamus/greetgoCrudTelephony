package com.akzam.mongoDbCrud.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record UserResponseDto (
        String id,
        String name,
        Integer yearOfBirth,
        String phoneNumber,
        String secondPhoneNumber,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime creationDate
) { }
