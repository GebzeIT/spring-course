package tr.bel.gebze.springcourse.performancelogging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Aspect
@Component
@Slf4j
class PerformanceLoggingAspect {

	//Concepts: https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#aop-introduction-defn
	//Examples: https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#aop-pointcuts
	@Around("@annotation(PerformanceLogged)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

		Instant start = Instant.now();

		Object retVal = joinPoint.proceed();

		log.info("{} duration {}ms", joinPoint.getSignature(), Duration.between(start, Instant.now()).toMillis());
		return retVal;
	}

}