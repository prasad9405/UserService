package com.admin.userservice.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Documented
@Target({ TYPE, FIELD, ANNOTATION_TYPE })
@Constraint(validatedBy = NameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidName {
    String message() default "Invalid Password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
