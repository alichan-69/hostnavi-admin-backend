package com.alichan.hostnavi.admin.application.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import com.alichan.hostnavi.admin.application.validation.validator.StringTypeValidator;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = StringTypeValidator.class)
public @interface StringType {
  String message() default "文字列型で入力してください";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
