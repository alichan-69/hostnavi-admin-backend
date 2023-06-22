package com.alichan.hostnavi.admin.error.exception;

import com.alichan.hostnavi.admin.application.response.ResponseMessageEnums;
import lombok.Getter;

@Getter
public class ServerFailException extends RuntimeException {
  private String message;

  public ServerFailException() {
    super(ResponseMessageEnums.SERVER_FAIL.getMessage());
  }

  public ServerFailException(String message) {
    super(message);
  }
}
