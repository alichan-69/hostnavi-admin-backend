package com.alichan.hostnavi.admin.error.exception;

import com.alichan.hostnavi.admin.application.response.ResponseMessageEnums;
import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
  private int code;

  public NotFoundException() {
    super(ResponseMessageEnums.NOT_FOUND.getMessage());
  }

  public NotFoundException(String message) {
    super(message);
  }
}
