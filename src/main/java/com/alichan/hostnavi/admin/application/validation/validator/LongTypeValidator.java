package com.alichan.hostnavi.admin.application.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.alichan.hostnavi.admin.application.validation.annotation.LongType;

public class LongTypeValidator implements ConstraintValidator<LongType, Long> {
  @Override
  public boolean isValid(Long value, ConstraintValidatorContext context) {
    return value instanceof Long || value == null;
  }
}
