package com.nifadh.pointofsales.validation.annotations;

import com.nifadh.pointofsales.validation.IntegerValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.CONSTRUCTOR, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = IntegerValidator.class)
public @interface IsInteger {
    public String message() default "Field should be a valid integer";

    Class<?>[] groups() default {};

    Class<? extends jakarta.validation.Payload>[] payload() default {};
}
