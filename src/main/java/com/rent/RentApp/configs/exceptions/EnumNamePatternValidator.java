package com.rent.RentApp.configs.exceptions;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.rent.RentApp.configs.exceptions.constraints.NamePattern;

public class EnumNamePatternValidator implements ConstraintValidator<NamePattern, Enum<?>> {

  @Override
  public void initialize(NamePattern constraintAnnotation) {
  }

  @Override
  public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }

    return value.name().trim().length() > 0;
  }

}
