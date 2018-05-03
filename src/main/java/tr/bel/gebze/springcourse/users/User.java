package tr.bel.gebze.springcourse.users;

import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;
import tr.bel.gebze.springcourse.profile.Profile;
import tr.bel.gebze.springcourse.security.Role;
import tr.bel.gebze.springcourse.validation.Tckn;
import tr.bel.gebze.springcourse.validation.ValidAdminPassword;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created on April, 2018
 *
 * @author destan
 */
@Getter
@Setter
@ToString
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ValidAdminPassword
public class User implements UserDetails {

	@Id
	@GeneratedValue
	private Long id;

	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	private Set<Item> items = new HashSet<>();

	@Size(min = 3)
	@Column(nullable = false)
	@NotEmpty
	private String username;

	@Column(unique = true)
	@Min(value = 10_000_000_000L, message = "{tr.bel.gebze.tckn_invalid_size}")
	@Max(value = 99_999_999_999L, message = "{tr.bel.gebze.tckn_invalid_size}")
	@Tckn
	private Long tckn;

	@Column(nullable = false)
	@NotEmpty
	private String password;

	@OneToOne(cascade = CascadeType.ALL)
	private Profile profile = new Profile();

	@Embedded
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<Role> authorities = new HashSet<>();

	@Override
	public Collection<Role> getAuthorities() {
//		return AuthorityUtils.createAuthorityList("USER");
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
