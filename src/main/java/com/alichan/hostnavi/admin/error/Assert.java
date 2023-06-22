package com.alichan.hostnavi.admin.error;

import com.alichan.hostnavi.admin.error.exception.FailValidationException;
import com.alichan.hostnavi.admin.error.exception.ForbbidenException;
import com.alichan.hostnavi.admin.error.exception.NotFoundException;
import com.alichan.hostnavi.admin.error.exception.ServerFailException;
import com.alichan.hostnavi.admin.error.exception.UnauthorizedException;

public class Assert {

  public static void unAuthorized() {
    throw new UnauthorizedException();
  }

  public static void unAuthorized(String message) {
    throw new UnauthorizedException(message);
  }

  public static void forbbiden() {
    throw new ForbbidenException();
  }

  public static void forbbiden(String message) {
    throw new ForbbidenException(message);
  }

  public static void notFound() {
    throw new NotFoundException();
  }

  public static void notFound(String message) {
    throw new NotFoundException(message);
  }

  public static void failedValidation() {
    throw new FailValidationException();
  }

  public static void failedValidation(String message) {
    throw new FailValidationException(message);
  }

  public static void serverFail() {
    throw new ServerFailException();
  }

  public static void serverFail(String message) {
    throw new ServerFailException(message);
  }

}
