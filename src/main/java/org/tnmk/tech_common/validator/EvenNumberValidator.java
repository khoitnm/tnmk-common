package org.tnmk.tech_common.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EvenNumberValidator implements ConstraintValidator<EvenNumber, Integer> {
    @Override
    public void initialize(EvenNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value == null || value % 2 == 0;
    }
}