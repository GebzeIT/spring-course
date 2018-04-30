package tr.bel.gebze.springcourse.hello;

import org.springframework.stereotype.Service;

/**
 * Created on April, 2018
 *
 * @author destan
 */
@Service
public class HelloWorldService implements GreetingsInterface {

	@Override
	public String greeting(String name) {
		return "Hello dear " + name;
	}

}
