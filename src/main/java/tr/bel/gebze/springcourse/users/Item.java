package tr.bel.gebze.springcourse.users;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created on May, 2018
 *
 * @author destan
 */
@Getter
@Setter
@ToString(exclude = "owner")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@ManyToOne
	private User owner;

	public Item(String name, User owner) {
		this.name = name;
		this.owner = owner;

		if(name.contains("default")) {
			//uncomment below code to observe rollback in registration
			//throw new RuntimeException("Demo exception for transactions");
		}
	}


}
