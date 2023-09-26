package com.akzam.mongoDbCrud.dto.user;

import com.akzam.mongoDbCrud.annotation.ValidPhoneNumber;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequestDto (

        @NotNull(message = "Name cannot be null")
        @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
        String name,

        @NotNull(message = "Year of birth cannot be null")
        @Min(
                value = 1900,
                message = "Year of Birth must be greater than or equal to 1900"
        )
        Integer yearOfBirth,

        @NotNull(message = "Phone number cannot be null")
        @ValidPhoneNumber
        String phoneNumber,

        @NotNull(message = "Second phone number cannot be null")
        @ValidPhoneNumber
        String secondPhoneNumber

) { }
