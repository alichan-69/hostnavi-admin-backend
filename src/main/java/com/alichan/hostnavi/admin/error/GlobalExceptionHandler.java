package com.alichan.hostnavi.admin.error;

import org.apache.ibatis.jdbc.Null;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.alichan.hostnavi.admin.application.response.Response;
import com.alichan.hostnavi.admin.error.exception.FailValidationException;
import com.alichan.hostnavi.admin.error.exception.ForbbidenException;
import com.alichan.hostnavi.admin.error.exception.NotFoundException;
import com.alichan.hostnavi.admin.error.exception.ServerFailException;
import com.alichan.hostnavi.admin.error.exception.UnauthorizedException;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ResponseBody
  @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(value = Exception.class)
  public Response<Null> handleException(Exception error) {
    return Response.serverFail();
  }

  @ResponseBody
  @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
  @ExceptionHandler(value = UnauthorizedException.class)
  public Response<Null> handleUnauthorizedException(UnauthorizedException error) {
    return Response.unauthorized(error.getMessage());
  }

  @ResponseBody
  @ResponseStatus(code = HttpStatus.FORBIDDEN)
  @ExceptionHandler(value = ForbbidenException.class)
  public Response<Null> handleForbbidenException(ForbbidenException error) {
    return Response.forbbiden(error.getMessage());
  }

  @ResponseBody
  @ResponseStatus(code = HttpStatus.NOT_FOUND)
  @ExceptionHandler(value = NotFoundException.class)
  public Response<Null> handleNotFoundException(NotFoundException error) {
    return Response.notFound(error.getMessage());
  }

  @ResponseBody
  @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
  @ExceptionHandler(value = BindException.class)
  public Response<Null> handleBindException(BindException error) {
    return Response.failValidation(error.getBindingResult().getFieldError().getDefaultMessage());
  }

  @ResponseBody
  @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
  @ExceptionHandler(value = FailValidationException.class)
  public Response<Null> handleFailValidationException(FailValidationException error) {
    return Response.failValidation(error.getMessage());
  }

  @ResponseBody
  @ExceptionHandler(value = ServerFailException.class)
  @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
  public Response<Null> handleServerFailException(ServerFailException error) {
    return Response.serverFail(error.getMessage());
  }
}

