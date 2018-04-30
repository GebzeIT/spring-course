package tr.bel.gebze.springcourse.hello;

/**
 * Created on April, 2018
 *
 * @author destan
 */
public class HiWorldService implements GreetingsInterface {

	@Override
	public String greeting(String name) {
		return "Hi dear " + name;
	}

}
