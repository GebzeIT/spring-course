package tr.bel.gebze.springcourse.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created on May, 2018
 *
 * @author destan
 */
@Controller
@RequestMapping("/admin")
class AdminController {

	@GetMapping
	String home() {
		return "admin/home";
	}

}
