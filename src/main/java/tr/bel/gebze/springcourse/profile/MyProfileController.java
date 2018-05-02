package tr.bel.gebze.springcourse.profile;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tr.bel.gebze.springcourse.ResourceNotFound;
import tr.bel.gebze.springcourse.users.UserRepository;

@Controller
@RequestMapping("/my-profile")
@Slf4j // private static final Logger log = LoggerFactory.getLogger(UsersController.class);
@AllArgsConstructor
public class MyProfileController {

	private final UserRepository userRepository;

	@GetMapping
	String list(@AuthenticationPrincipal Authentication authentication, Model model) {

		model.addAttribute("user", userRepository.findByUsername(authentication.getName()).orElseThrow(ResourceNotFound::new));
		return "profile";
	}

}
