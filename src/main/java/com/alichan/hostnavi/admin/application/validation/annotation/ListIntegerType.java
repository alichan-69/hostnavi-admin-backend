package com.alichan.hostnavi.admin.application.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import com.alichan.hostnavi.admin.application.validation.validator.ListIntegerTypeValidator;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = ListIntegerTypeValidator.class)
public @interface ListIntegerType {
  String message() default "listの要素をInteger型で入力してください";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
