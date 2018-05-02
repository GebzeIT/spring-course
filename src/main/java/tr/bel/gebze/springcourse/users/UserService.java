package tr.bel.gebze.springcourse.users;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created on May, 2018
 *
 * @author destan
 */
@Service
@AllArgsConstructor
@Slf4j
public class UserService {

	private final UserRepository userRepository;

	@Transactional
	public List<User> findAllUsers() {

		List<User> users = userRepository.findAll();

		// This is an N+1 problem
		for(User user : users) {
			log.info(user.getItems().size() + "");
		}

		return users;
	}

}
