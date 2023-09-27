package com.akzam.postgreSqlCrud.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponseDto (
        UUID id,
        String name,
        Integer yearOfBirth,
        String phoneNumber,
        String secondPhoneNumber,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime creationDate
) { }
