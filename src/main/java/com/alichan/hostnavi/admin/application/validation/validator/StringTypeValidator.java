package com.alichan.hostnavi.admin.application.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.alichan.hostnavi.admin.application.validation.annotation.StringType;

public class StringTypeValidator implements ConstraintValidator<StringType, String> {
  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return value instanceof String || value == null;
  }
}
