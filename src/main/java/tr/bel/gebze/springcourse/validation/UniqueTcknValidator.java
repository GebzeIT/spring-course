package tr.bel.gebze.springcourse.validation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import tr.bel.gebze.springcourse.users.User;
import tr.bel.gebze.springcourse.users.UserRepository;

/**
 * Created on May, 2018
 *
 * @author destan
 */
@Component
@AllArgsConstructor
public class UniqueTcknValidator implements Validator {

	private final UserRepository userRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		User user = (User) target;

		if(userRepository.findByTckn(user.getTckn()).isPresent()) {
			errors.rejectValue("tckn", "user.tckn_not_unique", "not unique tckn");
		}

	}
}
