package org.tnmk.tech_common.validator;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeanValidator {
  private static final Validator validator = new ValidatorConfig().validator();

  public static <T> void validate(T object) throws ConstraintViolationException {
    Set<ConstraintViolation<T>> violations = validator.validate(object);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException("BeanValidator %s\n%s".formatted(object, violations), violations);
    }
  }

  public static <T> void validateEquals(@NotNull T expectedValue, T actualValue, String message) throws IllegalStateException {
    if (!expectedValue.equals(actualValue)) {
      throw new IllegalStateException(message);
    }
  }
}
