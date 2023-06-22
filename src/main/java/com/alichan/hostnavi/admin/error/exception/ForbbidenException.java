package com.alichan.hostnavi.admin.error.exception;

import com.alichan.hostnavi.admin.application.response.ResponseMessageEnums;
import lombok.Getter;

@Getter
public class ForbbidenException extends RuntimeException {
  public ForbbidenException() {
    super(ResponseMessageEnums.FORBIDDEN.getMessage());
  }

  public ForbbidenException(String message) {
    super(message);
  }
}
