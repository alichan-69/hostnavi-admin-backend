package com.alichan.hostnavi.admin.application.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.alichan.hostnavi.admin.application.validation.annotation.IntegerType;

public class IntegerTypeValidator implements ConstraintValidator<IntegerType, Integer> {
  @Override
  public boolean isValid(Integer value, ConstraintValidatorContext context) {
    return value instanceof Integer || value == null;
  }
}
