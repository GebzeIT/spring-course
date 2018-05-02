package tr.bel.gebze.springcourse.profile;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created on May, 2018
 *
 * @author destan
 */
@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Slf4j
public class StatisticsService {

	private AtomicInteger count = new AtomicInteger();//If this was a request scoped bean then we could use int here

	StatisticsService() {
		log.info("StatisticsService is created.");
	}

	public int incrementAndGet() {
		return count.incrementAndGet();
	}
}
