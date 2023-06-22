package com.alichan.hostnavi.admin.error.exception;

import com.alichan.hostnavi.admin.application.response.ResponseMessageEnums;
import lombok.Getter;

@Getter
public class FailValidationException extends RuntimeException {
  public FailValidationException() {
    super(ResponseMessageEnums.FAIL_VALIDATION.getMessage());
  }

  public FailValidationException(String message) {
    super(message);
  }
}
