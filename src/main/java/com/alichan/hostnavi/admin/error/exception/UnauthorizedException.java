package com.alichan.hostnavi.admin.error.exception;

import com.alichan.hostnavi.admin.application.response.ResponseMessageEnums;
import lombok.Getter;

@Getter
public class UnauthorizedException extends RuntimeException {
  private int code;

  public UnauthorizedException() {
    super(ResponseMessageEnums.UNAUTHORIZED.getMessage());
  }

  public UnauthorizedException(String message) {
    super(message);
  }
}
