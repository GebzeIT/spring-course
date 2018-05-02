package tr.bel.gebze.springcourse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created on May, 2018
 *
 * @author destan
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("john").password("{noop}123qwe123").authorities("USER");
		auth.inMemoryAuthentication().withUser("joe").password("{noop}123qwe123").authorities("USER", "ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//@formatter:off
		http
			.authorizeRequests()
				.mvcMatchers("/").permitAll()
				.mvcMatchers("/admin/**").hasAnyAuthority("ADMIN")
				.anyRequest().authenticated()
			.and()
			.formLogin()
//				.loginPage("/login")
				.permitAll()
			.and()
			.logout()
				.permitAll();
		//@formatter:on
	}

}
