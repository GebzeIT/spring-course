package tr.bel.gebze.springcourse.security;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

/**
 * Created on May, 2018
 *
 * @author destan
 */
@Embeddable
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role implements GrantedAuthority {

	@NotEmpty
	private String authority;

	@Override
	public String getAuthority() {
		return authority;
	}
}
