package identity.TuanHuy.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = { DobValidator.class})
public @interface DobConstraint {
    // this ís three basic properties of an annotation , message , group , payload
    String message() default "{dob invalid 18 years old annotation custom}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    int min();
}
