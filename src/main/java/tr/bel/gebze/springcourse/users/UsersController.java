package tr.bel.gebze.springcourse.users;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tr.bel.gebze.springcourse.ResourceNotFound;
import tr.bel.gebze.springcourse.validation.UniqueTcknValidator;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Created on April, 2018
 *
 * @author destan
 */
@Controller
@RequestMapping("/users")
@Slf4j // private static final Logger log = LoggerFactory.getLogger(UsersController.class);
@AllArgsConstructor
class UsersController {

	private final UserRepository userRepository;

	private final UniqueTcknValidator uniqueTcknValidator;

	@InitBinder
	void addValidator(WebDataBinder webDataBinder) {
//		webDataBinder.addValidators(uniqueTcknValidator);
	}

	@GetMapping
	String list(@RequestParam(value = "success", defaultValue = "false") Boolean success, User user, Model model,
			@RequestParam(value = "tckn", defaultValue = "") Long tckn) {

		if(success) {
			return "success";
		}

//		User user = new User();
//		user.setName("Sample user");

		user.setTckn(tckn);
		model.addAttribute("user", user);
		model.addAttribute("userList", userRepository.findAll());
		return "users";
	}

	@GetMapping("/{id}")
	String details(Model model, @PathVariable("id") Long id) {

		Optional<User> user = userRepository.findById(id);

		model.addAttribute("user", user.orElseThrow(ResourceNotFound::new));

		return "user-details";
	}

	@PostMapping
	String create(@Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if(bindingResult.hasErrors()) {

			return "users";
		}

		userRepository.save(user);
		log.debug("User created {}", user);


//		User emptyUser = new User();// to reset the form
		//		model.addAttribute("user", emptyUser);
//		user = new User();// Won't work due to the fact that it's not a pointer but a reference.
//		return "users";


		redirectAttributes.addAttribute("tckn", user.getTckn());

		return "redirect:/users";// https://upload.wikimedia.org/wikipedia/commons/thumb/3/3c/PostRedirectGet_DoubleSubmitSolution.png/350px-PostRedirectGet_DoubleSubmitSolution.png
	}

	@ExceptionHandler(Exception.class)
	ModelAndView exceptionHandler() {
		//TODO

		ModelAndView model = new ModelAndView("users");
		model.addObject("errMsg", "this is Exception.class");

		return model;
	}

}
