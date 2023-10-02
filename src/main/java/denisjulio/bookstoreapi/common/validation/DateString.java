package denisjulio.bookstoreapi.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Documented
@Constraint(validatedBy = ValidDateValidator.class)
@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateString {

  String message() default "Invalid date format";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
