package tr.bel.gebze.springcourse.profile;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tr.bel.gebze.springcourse.users.User;
import tr.bel.gebze.springcourse.users.UserRepository;

/**
 * Created on May, 2018
 *
 * @author destan
 */
@Service
@AllArgsConstructor
@Slf4j
public class RegistrationService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

//	private final AuthenticationManager authenticationManager; // TODO https://github.com/spring-projects/spring-boot/issues/11136

//	private final HttpSession httpSession;

	void registerUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);

		// Auto login
//		login(user);
	}

//	private void login(User user) {
//		UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(user, user.getPassword());
//		Authentication auth = authenticationManager.authenticate(authReq);
//
//		SecurityContext sc = SecurityContextHolder.getContext();
//		sc.setAuthentication(auth);
//		httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);
//	}
}
