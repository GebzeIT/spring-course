package tr.bel.gebze.springcourse.users;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import tr.bel.gebze.springcourse.CopyUtil;
import tr.bel.gebze.springcourse.ResourceNotFound;
import tr.bel.gebze.springcourse.performancelogging.PerformanceLogged;
import tr.bel.gebze.springcourse.profile.Profile;
import tr.bel.gebze.springcourse.profile.ProfileRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created on May, 2018
 *
 * @author destan
 */
@Service
@AllArgsConstructor
@Slf4j
public class UserService {

	private static final String USERS_CACHE_NAME = "users";

	private final UserRepository userRepository;

	private  final ProfileRepository profileRepository;

	private final ItemPrinter itemPrinter;

	private final CacheManager cacheManager;

	@PerformanceLogged
//	@Cacheable(USERS_CACHE_NAME)
	public List<User> findAllUsers() {

		log.info("findAllUsers without cache");
		return itemPrinter.internal();// calling this.internal() won't work when using userRepository.findAll()
	}

	@CacheEvict(USERS_CACHE_NAME)
	public void clearCache() {

		Cache usersCache = cacheManager.getCache(USERS_CACHE_NAME);
		//you can use the cache here manually

		log.info("findAllUsers cache cleared");
	}

	@PerformanceLogged
	@Transactional
	public void updateProfile(Profile newProfile) {
		Profile profile = profileRepository.findById(newProfile.getId()).orElseThrow(ResourceNotFound::new);
//		profile.setAddress(newProfile.getAddress());
//		profile.setAge(newProfile.getAge());
//		profile.setPhoneNumber(newProfile.getPhoneNumber());

		CopyUtil.copy(newProfile, profile);

//		profileRepository.save(newProfile); // we don't need this because our method has @Transactional
	}

	@Transactional
	public List<User> internal() {
		log.debug("Querying users...");
				List<User> users = userRepository.findAllUsersUserWithItems();
//		List<User> users = userRepository.findAll();

		log.debug("Getting items...");
		// This is an N+1 problem
		for(User user : users) {

			log.debug("Getting item of user {}", user.getUsername());
			log.info(user.getItems().size() + "");
		}

		return users;
	}


}

@Service
@Slf4j
@AllArgsConstructor
class ItemPrinter {

	private final UserRepository userRepository;

	@Transactional
	public List<User> internal() {
		log.debug("Querying users...");
		//		List<User> users = userRepository.findAllUsersUserWithItems();
		List<User> users = userRepository.findAll();

		log.debug("Getting items...");
		// This is an N+1 problem
		for(User user : users) {

			log.debug("Getting item of user {}", user.getUsername());
			log.info(user.getItems().size() + "");
		}

		return users;
	}
}
