package com.akzam.postgreSqlCrud.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record Filter (

        @NotNull(message = "Offset id cannot be null")
        @Min(value = 0, message = "Offset must be greater than or equal to 0")
        Long offset,

        @NotNull(message = "Limit id cannot be null")
        @Min(value = 0, message = "Limit must be greater than or equal to 0")
        Long limit

) { }
