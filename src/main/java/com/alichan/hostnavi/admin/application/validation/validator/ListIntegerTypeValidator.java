package com.alichan.hostnavi.admin.application.validation.validator;

import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.alichan.hostnavi.admin.application.validation.annotation.ListIntegerType;

public class ListIntegerTypeValidator
    implements ConstraintValidator<ListIntegerType, List<Integer>> {
  @Override
  public boolean isValid(List<Integer> values, ConstraintValidatorContext context) {
    return values.stream().allMatch(value -> value instanceof Integer || value == null);
  }
}
