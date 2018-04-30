package tr.bel.gebze.springcourse.hello;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created on April, 2018
 *
 * @author destan
 */
@Controller
public class HelloWorldController {

	private final GreetingsInterface greetingsInterface;

	public HelloWorldController(@Qualifier("hiWorldService") GreetingsInterface greetingsInterface) {
		this.greetingsInterface = greetingsInterface;
	}

	@GetMapping("/hello")
	String helloWorld(Model model, @RequestParam(value = "name", required = false) String name) {

		model.addAttribute("username", name);
		model.addAttribute("message", greetingsInterface.greeting(name));

		return "hello";
	}

}
