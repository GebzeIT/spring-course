package tr.bel.gebze.springcourse.profile;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import tr.bel.gebze.springcourse.ResourceNotFound;
import tr.bel.gebze.springcourse.users.UserRepository;

@Controller
@RequestMapping("/users")
@Slf4j // private static final Logger log = LoggerFactory.getLogger(UsersController.class);
@AllArgsConstructor
public class ProfileController {

	private final UserRepository userRepository;

	@GetMapping("/{userId}/profile")
	String list(@PathVariable("userId") Long id, Model model) {

		model.addAttribute("profile", userRepository.findById(id).orElseThrow(ResourceNotFound::new).getProfile());
		return "profile";
	}

}
