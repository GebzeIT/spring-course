package tr.bel.gebze.springcourse.users;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.bel.gebze.springcourse.ResourceNotFound;

import java.util.List;

/**
 * Created on April, 2018
 *
 * @author destan
 */
@RestController
@RequestMapping("/api/v1/users")
@Slf4j
@AllArgsConstructor
public class UsersRestController {

	private final UserRepository userRepository;

	@GetMapping("/check-tckn-unique")
	boolean checkIfTcknIsUnique(@RequestParam("tckn") Long tckn) {
		return !userRepository.findByTckn(tckn).isPresent();
	}

	@GetMapping
	ResponseEntity<List<User>> list() {
		return ResponseEntity.ok(userRepository.findAll());
	}

	@GetMapping("/{id}")
	ResponseEntity<User> get(@PathVariable Long id) {
		return ResponseEntity.ok(userRepository.findById(id).orElseThrow(ResourceNotFound::new));
	}

	@PostMapping
	ResponseEntity<Void> create(@RequestBody User user) {
		userRepository.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/{id}")
	ResponseEntity<User> delete(@PathVariable Long id) {
		userRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
}
