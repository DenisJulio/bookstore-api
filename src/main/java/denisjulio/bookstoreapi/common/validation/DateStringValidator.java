package denisjulio.bookstoreapi.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DateStringValidator implements ConstraintValidator<DateString, String> {

  @Override
  public void initialize(DateString constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null || value.isEmpty())
      return false;
    try {
      LocalDate.parse(value);
      return true;
    } catch (DateTimeParseException e) {
      return false;
    }
  }
}
