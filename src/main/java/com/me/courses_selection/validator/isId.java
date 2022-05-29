package com.me.courses_selection.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD,FIELD,ANNOTATION_TYPE,CONSTRUCTOR,PARAMETER,TYPE_USE })
@Retention(RUNTIME)
/**
 * id格式判断注解
 */
@Documented
@Constraint(validatedBy = {isIdValidator.class})
public @interface isId {
    boolean required() default true;
    String message() default "账号格式错误";

    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default {};

}

