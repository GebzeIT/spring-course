package tr.bel.gebze.springcourse.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created on May, 2018
 *
 * @author destan
 */
public class TcknValidator implements ConstraintValidator<Tckn, Long> {

	@Override
	public void initialize(Tckn constraintAnnotation) {

	}

	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {

		if(value.toString().startsWith("9")) {
			return false;
		}

		return true;
	}
}
