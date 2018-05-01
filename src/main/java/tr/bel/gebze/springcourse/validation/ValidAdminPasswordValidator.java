package tr.bel.gebze.springcourse.validation;

import tr.bel.gebze.springcourse.users.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created on May, 2018
 *
 * @author destan
 */
public class ValidAdminPasswordValidator implements ConstraintValidator<ValidAdminPassword, User> {

	@Override
	public void initialize(ValidAdminPassword constraintAnnotation) {

	}

	@Override
	public boolean isValid(User user, ConstraintValidatorContext context) {

		return !user.getUsername().equalsIgnoreCase("admin") || user.getPassword().length() >= 10;
	}
}
