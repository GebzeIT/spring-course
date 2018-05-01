package tr.bel.gebze.springcourse.users;

import lombok.*;
import tr.bel.gebze.springcourse.profile.Profile;
import tr.bel.gebze.springcourse.validation.Tckn;
import tr.bel.gebze.springcourse.validation.ValidAdminPassword;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
public class User {

	@Id
	@GeneratedValue
	private Long id;

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

}
