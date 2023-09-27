package com.akzam.postgreSqlCrud.exception;

import com.akzam.postgreSqlCrud.dto.apiException.ApiExceptionResponseDto;
import com.akzam.postgreSqlCrud.dto.apiException.ApiValidationExceptionResponseDto;
import jakarta.validation.ConstraintViolation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ApiExceptionResponseFactory {

    private final ZoneId UTC = ZoneId.of("Z");

    public ApiExceptionResponseDto createApiExceptionResponseDto(HttpStatus httpStatus, String errorMessage) {
        return ApiExceptionResponseDto.builder()
                .errorCode(httpStatus.value())
                .httpStatus(httpStatus)
                .timestamp(ZonedDateTime.now(UTC))
                .errorMessage(errorMessage)
                .build();
    }

    public ApiValidationExceptionResponseDto createApiValidationExceptionResponseDto(BindingResult bindingResult) {
        Map<String, String> errorFields = buildErrorFields(bindingResult);
        return ApiValidationExceptionResponseDto.builder()
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .timestamp(ZonedDateTime.now(UTC))
                .errorFields(errorFields)
                .build();
    }

    public ApiValidationExceptionResponseDto createApiValidationExceptionResponseDto(
            Set<ConstraintViolation<?>> violations
    ) {
        Map<String, String> errorFields = buildErrorFields(violations);
        return ApiValidationExceptionResponseDto.builder()
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .timestamp(ZonedDateTime.now(UTC))
                .errorFields(errorFields)
                .build();
    }

    private Map<String, String> buildErrorFields(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .collect(
                        Collectors.toMap(
                                FieldError::getField,
                                FieldError::getDefaultMessage,
                                (existing, replacement) -> existing + "; " + replacement
                        )
                );
    }

    private Map<String, String> buildErrorFields(Set<ConstraintViolation<?>> violations) {
        return violations.stream()
                .collect(
                        Collectors.toMap(
                                this::extractFieldName,
                                ConstraintViolation::getMessage,
                                (existing, replacement) -> existing + "; " + replacement
                        )
                );
    }

    private String extractFieldName(ConstraintViolation<?> violation) {
        String[] parts = violation.getPropertyPath().toString().split("\\.");
        return parts[parts.length - 1];
    }

}
