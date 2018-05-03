package tr.bel.gebze.springcourse;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import tr.bel.gebze.springcourse.profile.Profile;
import tr.bel.gebze.springcourse.security.Role;
import tr.bel.gebze.springcourse.users.Item;
import tr.bel.gebze.springcourse.users.User;
import tr.bel.gebze.springcourse.users.UserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

	private final PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {


		Profile profile = Profile.builder()
				.address("izmir")
				.age(33)
				.phoneNumber("505 99 123")
				.build();

		User user = User.builder()
				.username("john")
				.password(passwordEncoder.encode("123qwe123"))
				.tckn(12345678901L)
				.profile(profile)
				.authorities(new HashSet<>())
				.build();

		user.getAuthorities().add(new Role("USER"));

		Set<Item> items1 = new HashSet<>(Arrays.asList(new Item("x1", user), new Item("x2", user)));
		user.setItems(items1);


		Profile profile2 = Profile.builder()
				.address("izmir")
				.age(33)
				.phoneNumber("505 99 987")
				.build();

		User user2 = User.builder()
				.username("joe")
				.password(passwordEncoder.encode("123qwe123"))
				.tckn(12345678902L)
				.profile(profile2)
				.authorities(new HashSet<>())
				.build();

		user2.getAuthorities().add(new Role("USER"));

		Set<Item> items2 = new HashSet<>(Arrays.asList(new Item("y1", user2), new Item("y2", user2)));
		user2.setItems(items2);


		userRepository.save(user);
		userRepository.save(user2);
		log.info("Default user created {}", user);
	}
}
