package com.nifadh.pointofsales.validation;

import com.nifadh.pointofsales.validation.annotations.IsInteger;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class IntegerValidator implements ConstraintValidator<IsInteger, Object> {
    @Override
    public void initialize(IsInteger constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object input, ConstraintValidatorContext constraintValidatorContext) {
        try {
            Integer.parseInt(input.toString());
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
