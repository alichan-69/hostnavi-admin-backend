package com.alichan.hostnavi.admin.application.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.alichan.hostnavi.admin.application.validation.annotation.ByteType;

public class ByteTypeValidator implements ConstraintValidator<ByteType, Byte> {
  @Override
  public boolean isValid(Byte value, ConstraintValidatorContext context) {
    return value instanceof Byte || value == null;
  }
}
