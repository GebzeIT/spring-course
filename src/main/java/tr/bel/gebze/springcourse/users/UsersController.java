package tr.bel.gebze.springcourse.users;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created on April, 2018
 *
 * @author destan
 */
@Controller
@RequestMapping("/users")
@Slf4j // private static final Logger log = LoggerFactory.getLogger(UsersController.class);
public class UsersController {

	private final UserRepository userRepository;

	public UsersController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping
	String list(@RequestParam(value = "success", defaultValue = "false") Boolean success, User user, Model model,
			@RequestParam(value = "user.name", defaultValue = "") String name) {

		if(success) {
			return "success";
		}

//		User user = new User();
//		user.setName("Sample user");

		user.setUsername(name);
		model.addAttribute("user", user);
		model.addAttribute("userList", userRepository.findAll());
		return "users";
	}

	@GetMapping("/{id}")
	String details(Model model, @PathVariable("id") int id) {

		model.addAttribute("userId", id);

		return "hello";
	}

	@PostMapping
	String create(User user, RedirectAttributes redirectAttributes) {

		userRepository.save(user);
		log.debug("User created {}", user);


//		User emptyUser = new User();// to reset the form
		//		model.addAttribute("user", emptyUser);
//		user = new User();// Won't work due to the fact that it's not a pointer but a reference.
//		return "users";

		redirectAttributes.addAttribute("user.name", user.getUsername());
		redirectAttributes.addAttribute("user.age", user.getAge());

		return "redirect:/users";// https://upload.wikimedia.org/wikipedia/commons/thumb/3/3c/PostRedirectGet_DoubleSubmitSolution.png/350px-PostRedirectGet_DoubleSubmitSolution.png
	}

}
