package tr.bel.gebze.springcourse.profile;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tr.bel.gebze.springcourse.users.User;

/**
 * Created on May, 2018
 *
 * @author destan
 */
@Controller
@RequestMapping("/register")
public class RegistrationController {

	@GetMapping
	String register(Model model, User user) {

		return "registration";
	}

}
