package tr.bel.gebze.springcourse.users;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tr.bel.gebze.springcourse.ResourceNotFound;
import tr.bel.gebze.springcourse.profile.StatisticsService;

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

	private final StatisticsService statisticsService;

	private final UserService userService;

	@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
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
		model.addAttribute("userList", userService.findAllUsers());
		model.addAttribute("pageViewCount", statisticsService.incrementAndGet());
		return "users";
	}

	@GetMapping("/clear-cache")
	@ResponseBody
	ResponseEntity<Void> clearCache() {
		userService.clearCache();
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{id}")
	String details(Model model, @PathVariable("id") Long id) {

		Optional<User> user = userRepository.findById(id);

		model.addAttribute("user", user.orElseThrow(ResourceNotFound::new));

		return "user-details";
	}

}
