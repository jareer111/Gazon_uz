package com.noobs.validators;

import com.noobs.gazonuz.domains.BaseEntity;
import com.noobs.gazonuz.repositories.BaseDAO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class UniqueFieldValidator implements ConstraintValidator<Unique, String> {

    String column;
    Class<? extends BaseEntity> clazz;

    BaseDAO<BaseEntity, String> baseDAO = new BaseDAO<>() {
    };

    @Override
    public void initialize(Unique constraintAnnotation) {
        this.column = constraintAnnotation.columnName();
        this.clazz = constraintAnnotation.clazz();
    }

    @Override
    public boolean isValid(String value , ConstraintValidatorContext context) {

        List value1 = baseDAO.em.createQuery("select t from " + clazz.getSimpleName() + " t where t." + column + " ilike :value")
                .setParameter("value" , value).getResultList();
        return value1.isEmpty();


    }
}