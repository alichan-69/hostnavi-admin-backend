package com.alichan.hostnavi.admin.application.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;
import com.alichan.hostnavi.admin.application.validation.Util;
import com.alichan.hostnavi.admin.application.validation.annotation.FileExtension;

public class FileExtensionValidator implements ConstraintValidator<FileExtension, MultipartFile> {
  @Override
  public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
    return Util.isValidFileExtension(file);
  }
}
