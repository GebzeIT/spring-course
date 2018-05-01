package tr.bel.gebze.springcourse;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tr.bel.gebze.springcourse.profile.Profile;
import tr.bel.gebze.springcourse.users.User;
import tr.bel.gebze.springcourse.users.UserRepository;

/**
 * Created on May, 2018
 *
 * @author destan
 */
@org.springframework.context.annotation.Profile("dev")
@Component
@AllArgsConstructor
@Slf4j
public class InitialDataGenerator implements CommandLineRunner {

	private final UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {

		Profile profile = Profile.builder()
				.address("izmir")
				.age(33)
				.phoneNumber("505 99 123")
				.build();

		User user = User.builder()
				.username("john")
				.password("123qwe123")
				.tckn(123123L)
				.profile(profile)
				.build();

		userRepository.save(user);
		log.info("Default user created {}", user);
	}
}
