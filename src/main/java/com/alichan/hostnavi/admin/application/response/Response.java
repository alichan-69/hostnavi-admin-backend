package com.alichan.hostnavi.admin.application.response;

import lombok.Getter;

@Getter
public class Response<T> {
  private String message;
  private T data;

  private Response(String message, T data) {
    this.message = message;
    this.data = data;
  }

  public static <T> Response<T> success(T data) {
    return new Response<T>(ResponseMessageEnums.SUCCESS.getMessage(), data);
  }

  public static <T> Response<T> success() {
    return new Response<T>(ResponseMessageEnums.SUCCESS.getMessage(), null);
  }

  public static <T> Response<T> unauthorized() {
    return new Response<T>(ResponseMessageEnums.UNAUTHORIZED.getMessage(), null);
  }

  public static <T> Response<T> unauthorized(String message) {
    return new Response<T>(message, null);
  }

  public static <T> Response<T> forbbiden() {
    return new Response<T>(ResponseMessageEnums.FORBIDDEN.getMessage(), null);
  }

  public static <T> Response<T> forbbiden(String message) {
    return new Response<T>(message, null);
  }

  public static <T> Response<T> notFound() {
    return new Response<T>(ResponseMessageEnums.NOT_FOUND.getMessage(), null);
  }

  public static <T> Response<T> notFound(String message) {
    return new Response<T>(message, null);
  }

  public static <T> Response<T> failValidation() {
    return new Response<T>(ResponseMessageEnums.FAIL_VALIDATION.getMessage(), null);
  }

  public static <T> Response<T> failValidation(String message) {
    return new Response<T>(message, null);
  }

  public static <T> Response<T> serverFail() {
    return new Response<T>(ResponseMessageEnums.SERVER_FAIL.getMessage(), null);
  }

  public static <T> Response<T> serverFail(String message) {
    return new Response<T>(message, null);
  }
}
