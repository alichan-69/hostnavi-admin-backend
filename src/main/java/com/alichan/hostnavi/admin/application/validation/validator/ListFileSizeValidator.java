package com.alichan.hostnavi.admin.application.validation.validator;

import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;
import com.alichan.hostnavi.admin.application.validation.Util;
import com.alichan.hostnavi.admin.application.validation.annotation.ListFileSize;

public class ListFileSizeValidator
    implements ConstraintValidator<ListFileSize, List<MultipartFile>> {
  @Override
  public boolean isValid(List<MultipartFile> values, ConstraintValidatorContext context) {
    return values.stream().allMatch(value -> Util.isValidFileSize(value));
  }
}
