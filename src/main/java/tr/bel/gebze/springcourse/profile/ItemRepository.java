package tr.bel.gebze.springcourse.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.bel.gebze.springcourse.users.Item;

/**
 * Created on May, 2018
 *
 * @author destan
 */
interface ItemRepository extends JpaRepository<Item, Long> {

}
