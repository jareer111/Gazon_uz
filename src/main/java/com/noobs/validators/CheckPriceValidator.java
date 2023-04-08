package com.noobs.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CheckPriceValidator implements ConstraintValidator<ValidPrice, Double> {

    String message;


    @Override
    public boolean isValid(Double price, ConstraintValidatorContext constraintValidatorContext) {
        return (price > 0 && price < 1000000);
    }

}
