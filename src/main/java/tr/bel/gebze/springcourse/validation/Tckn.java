package tr.bel.gebze.springcourse.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TcknValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Tckn {

    String message() default "{tr.bel.gebze.tckn_invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}