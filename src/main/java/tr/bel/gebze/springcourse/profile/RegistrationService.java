package tr.bel.gebze.springcourse.profile;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
import tr.bel.gebze.springcourse.users.Item;
import tr.bel.gebze.springcourse.users.User;
import tr.bel.gebze.springcourse.users.UserRepository;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

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

	private final ItemRepository itemRepository;

	private final AuthenticationManager authenticationManager;

	private final HttpSession httpSession;

	@Transactional
	public void registerUser(User user) {

		final String clearTextPassword = user.getPassword();
		user.setPassword(passwordEncoder.encode(clearTextPassword));
		userRepository.save(user);

		Item defaultItem = new Item("default item", user);
		itemRepository.save(defaultItem);

		//Auto login
		login(user, clearTextPassword);
	}

	private void login(User user, String password) {
		try {
			UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(user, password);
			Authentication auth = authenticationManager.authenticate(authReq);

			SecurityContext sc = SecurityContextHolder.getContext();
			sc.setAuthentication(auth);
			httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
