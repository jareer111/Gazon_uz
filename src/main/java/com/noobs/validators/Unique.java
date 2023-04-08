package com.noobs.validators;

import com.noobs.gazonuz.domains.BaseEntity;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target( {METHOD , FIELD , ANNOTATION_TYPE , CONSTRUCTOR , PARAMETER , TYPE_USE} )
@Retention( RUNTIME )
@Documented
@Constraint( validatedBy = {UniqueFieldValidator.class} )
public @interface Unique {

    Class<? extends BaseEntity> clazz();

    String columnName();


    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String message();
}

