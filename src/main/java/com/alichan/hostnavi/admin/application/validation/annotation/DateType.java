package com.alichan.hostnavi.admin.application.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import com.alichan.hostnavi.admin.application.validation.validator.DateTypeValidator;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = DateTypeValidator.class)
public @interface DateType {
  String message() default "日付型で入力してください";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
