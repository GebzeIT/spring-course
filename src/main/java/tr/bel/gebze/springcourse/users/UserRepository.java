package tr.bel.gebze.springcourse.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * https://docs.spring.io/spring-data/jpa/docs/2.1.0.M1/reference/html/#jpa.query-methods.query-creation
 */
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

	Optional<User> findByTckn(Long tckn);

	@Query("SELECT DISTINCT u FROM User u JOIN FETCH u.items JOIN FETCH u.profile")
	List<User> findAllUsersUserWithItems();

}
