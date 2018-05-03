package tr.bel.gebze.springcourse.profile;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tr.bel.gebze.springcourse.ResourceNotFound;
import tr.bel.gebze.springcourse.users.User;
import tr.bel.gebze.springcourse.users.UserRepository;
import tr.bel.gebze.springcourse.users.UserService;

@Controller
@RequestMapping("/users")
@Slf4j // private static final Logger log = LoggerFactory.getLogger(UsersController.class);
@AllArgsConstructor
public class ProfileController {

	private final UserRepository userRepository;

	private final UserService userService;

	@GetMapping("/{userId}/profile")
	String list(@PathVariable("userId") Long id, Model model) {

		model.addAttribute("user", userRepository.findById(id).orElseThrow(ResourceNotFound::new));
		return "profile";
	}

	@GetMapping("/{userId}/profile/update")
	String updateProfileForm(@PathVariable("userId") Long id, Model model) {

		model.addAttribute("user", userRepository.findById(id).orElseThrow(ResourceNotFound::new));
		return "profile-update";
	}

//	@PreAuthorize("#id == #currentUser.id")
	@PreAuthorize("isProfileOwner(#id)")
	@PostMapping("/{userId}/profile/update")
	String updateProfile(@PathVariable("userId") Long id, User user, RedirectAttributes redirectAttributes, @AuthenticationPrincipal User currentUser) {

		// Manual check
//		if(!currentUser.getId().equals(id)) {
//			throw new AccessDeniedException("You are not the owner");
//		}

		userService.updateProfile(user.getProfile());

		redirectAttributes.addAttribute("updated", "true");
		return "redirect:/users/" + id + "/profile/update";
	}

}
