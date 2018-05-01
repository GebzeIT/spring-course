package tr.bel.gebze.springcourse.users;

import lombok.*;
import tr.bel.gebze.springcourse.profile.Profile;

import javax.persistence.*;

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
public class User {

	@Id
	@GeneratedValue
	private Long id;

	private String username;

	private Long tckn;

	private String password;

	@OneToOne(cascade = CascadeType.ALL)
	private Profile profile = new Profile();

}
