package tr.bel.gebze.springcourse;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import tr.bel.gebze.springcourse.security.UserDetailsServiceImpl;

/**
 * Created on May, 2018
 *
 * @author destan
 */
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("john").password("{noop}123qwe123").authorities("USER");
//		auth.inMemoryAuthentication().withUser("joe").password("{noop}123qwe123").authorities("USER", "ADMIN");
//	}

	private final UserDetailsServiceImpl userDetails;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetails);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//@formatter:off
		http
			.csrf().ignoringAntMatchers("/h2-console/**").and()
			.headers().frameOptions().disable().and() // required for h2-console to work
			.authorizeRequests()
				.mvcMatchers("/", "/register/**", "/api/v1/users/check-tckn-unique/**", "/styles/**").permitAll()
				.mvcMatchers("/admin/**").hasAnyAuthority("ADMIN")
				.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
			.and()
			.logout()
				.permitAll();
		//@formatter:on
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// because https://github.com/spring-projects/spring-boot/issues/11136
		return super.authenticationManagerBean();
	}

}
