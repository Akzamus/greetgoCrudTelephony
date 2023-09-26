package com.akzam.mongoDbCrud.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {})
@Pattern(
        regexp = "^[0-9a-fA-F]{24}$",
        message = "ObjectId must be a valid 24-character hexadecimal code"
)
public @interface ValidObjectId {
    String message() default "Invalid ObjectId format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
