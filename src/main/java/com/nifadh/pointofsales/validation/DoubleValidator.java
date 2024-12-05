package com.nifadh.pointofsales.validation;

import com.nifadh.pointofsales.validation.annotations.IsDouble;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DoubleValidator implements ConstraintValidator<IsDouble, Object> {
    @Override
    public void initialize(IsDouble constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object input, ConstraintValidatorContext constraintValidatorContext) {
        try {
            Double.parseDouble(input.toString());
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
