package tr.bel.gebze.springcourse.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidAdminPasswordValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAdminPassword {

    String message() default "{tr.bel.gebze.admin_password_weak}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}