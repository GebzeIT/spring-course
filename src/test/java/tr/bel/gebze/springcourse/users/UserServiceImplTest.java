package tr.bel.gebze.springcourse.users;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import tr.bel.gebze.springcourse.profile.Profile;
import tr.bel.gebze.springcourse.security.UserDetailsServiceImpl;

import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
public class UserServiceImplTest {
 
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
 
    @MockBean
    private UserRepository userRepository;

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public UserDetailsServiceImpl userDetailsService(UserRepository employeeRepository) {
            return new UserDetailsServiceImpl(employeeRepository);
        }
    }

    @Before
    public void setUp() {
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
                .build();

        Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.ofNullable(user));
    }

    @Test
    public void whenValidName_thenEmployeeShouldBeFound() {
        String name = "john";
        UserDetails user = userDetailsService.loadUserByUsername(name);

        assertThat(user.getUsername()).isEqualTo(name);
    }
}