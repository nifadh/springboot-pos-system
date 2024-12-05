package com.nifadh.pointofsales.validation.annotations;

import com.nifadh.pointofsales.validation.DoubleValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.CONSTRUCTOR, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = DoubleValidator.class)
public @interface IsDouble {
    public String message() default "Field should be a valid Double";

    Class<?>[] groups() default {};

    Class<? extends jakarta.validation.Payload>[] payload() default {};
}
