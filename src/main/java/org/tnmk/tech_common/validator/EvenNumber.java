package org.tnmk.tech_common.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@NotNull
@Constraint(validatedBy = EvenNumberValidator.class)
@ReportAsSingleViolation
public @interface EvenNumber {
    String message() default "Number must be even";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}