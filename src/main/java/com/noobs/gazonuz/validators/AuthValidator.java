package com.noobs.gazonuz.validators;


import com.noobs.gazonuz.dtos.UserCreatedDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthValidator {


    public Set<ConstraintViolation<UserCreatedDto>> validate(UserCreatedDto dto) {

        try ( ValidatorFactory factory = Validation.buildDefaultValidatorFactory() ) {
            Validator validator = factory.getValidator();
            return validator.validate(dto);
        }

    }
}
