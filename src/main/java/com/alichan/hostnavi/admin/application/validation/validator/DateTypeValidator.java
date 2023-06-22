package com.alichan.hostnavi.admin.application.validation.validator;

import java.util.Date;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.alichan.hostnavi.admin.application.validation.annotation.DateType;

public class DateTypeValidator implements ConstraintValidator<DateType, Date> {
  @Override
  public boolean isValid(Date value, ConstraintValidatorContext context) {
    return value instanceof Date || value == null;
  }
}
