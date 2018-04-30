package tr.bel.gebze.springcourse.users;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created on April, 2018
 *
 * @author destan
 */
@Getter
@Setter
@ToString
@Entity
public class User {

	@Id
	@GeneratedValue
	private Long id;

	private String username;

	private Integer age;

}
