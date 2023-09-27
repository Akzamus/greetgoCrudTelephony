package com.akzam.postgreSqlCrud.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {})
@Pattern(
        regexp = "\\+7-\\(\\d{3}\\)-\\d{3}-\\d{2}-\\d{2}",
        message = "Phone Number must be in the format +7-(XXX)-XXX-XX-XX"
)
public @interface ValidPhoneNumber {
    String message() default "Invalid phone number format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
