package dance.brain.scbtspring.validation;


import dance.brain.scbtspring.validation.validator.UserInfoValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UserInfoValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface UserInfo {
    String message() default "firstname and lastname must be filled in";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
