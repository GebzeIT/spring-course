package tr.bel.gebze.springcourse.users;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import tr.bel.gebze.springcourse.profile.Profile;

import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
 
    @Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findAllWitProfiles() {
        // given
        Profile profile = Profile.builder()
                .address("izmir")
                .age(33)
                .phoneNumber("505 99 123")
                .build();

        User user = User.builder()
                .username("john")
                .password("123qwe123")
                .tckn(12345678901L)
                .profile(profile)
                .authorities(new HashSet<>())
				.items(new HashSet<>())
                .build();

		Item item = new Item.ItemBuilder()
				.name("some item")
				.owner(user)
				.build();

		user.getItems().add(item);

        entityManager.persist(user);
        entityManager.flush();

        // when
        List<User> users = userRepository.findAllUsersUserWithItems();

        // then
        assertThat(users.size()).isGreaterThan(0);
        assertThat(users.get(0).getProfile()).isNotNull();
    }
 
}