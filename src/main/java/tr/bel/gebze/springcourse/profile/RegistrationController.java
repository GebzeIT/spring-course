package tr.bel.gebze.springcourse.profile;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tr.bel.gebze.springcourse.users.User;
import tr.bel.gebze.springcourse.users.UserRepository;
import tr.bel.gebze.springcourse.validation.UniqueTcknValidator;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.sql.SQLException;

/**
 * Created on May, 2018
 *
 * @author destan
 */
@Controller
@RequestMapping("/register")
@AllArgsConstructor
@Slf4j
public class RegistrationController {

	private final UserRepository userRepository;

	private final RegistrationService registrationService;

	private final UniqueTcknValidator uniqueTcknValidator;

	@InitBinder
	void addValidator(WebDataBinder webDataBinder) {
		//		webDataBinder.addValidators(uniqueTcknValidator);
	}

	@GetMapping
	String register(Model model, User user) {

		return "registration";
	}

	@PostMapping
	String create(@Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if(bindingResult.hasErrors()) {
			return "registration";
		}

		registrationService.registerUser(user);
		log.debug("User created {}", user);


		//		User emptyUser = new User();// to reset the form
		//		model.addAttribute("user", emptyUser);
		//		user = new User();// Won't work due to the fact that it's not a pointer but a reference.
		//		return "users";


		redirectAttributes.addAttribute("tckn", user.getTckn());
		redirectAttributes.addAttribute("registration", "success");

		return "redirect:/";// https://upload.wikimedia.org/wikipedia/commons/thumb/3/3c/PostRedirectGet_DoubleSubmitSolution.png/350px-PostRedirectGet_DoubleSubmitSolution.png
	}

	@ExceptionHandler({DataIntegrityViolationException.class, ConstraintViolationException.class, SQLException.class})
	String exceptionHandler(Exception e) {
		log.error("There is an exception with details: " + e.getMessage(), e);
		return "constraint-error";
	}

}
