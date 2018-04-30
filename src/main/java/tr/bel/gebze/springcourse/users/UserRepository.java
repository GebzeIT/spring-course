package tr.bel.gebze.springcourse.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * https://docs.spring.io/spring-data/jpa/docs/2.1.0.M1/reference/html/#jpa.query-methods.query-creation
 */
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

}
