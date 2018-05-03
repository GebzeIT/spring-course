package tr.bel.gebze.springcourse;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created on May, 2018
 *
 * @author destan
 */
@Component
@Slf4j
@AllArgsConstructor
class ScheduledLogger {

	private final SchedulerFactoryBean schedulerFactoryBean;

	//http://www.cronmaker.com
	@Scheduled(fixedRate = 2000)
	public void demoLog() {
		log.info("Demo {}", System.currentTimeMillis());
	}

	//@PostConstruct
	void init() throws SchedulerException {
		final JobDetail jobDetail = JobBuilder.newJob(ExampleJob.class)
				.usingJobData("someData", System.currentTimeMillis())
				.build();

		final Trigger trigger = TriggerBuilder.newTrigger()
				.withSchedule(SimpleScheduleBuilder.repeatSecondlyForever())
				.build();

		Date nextTriggerTime = schedulerFactoryBean.getScheduler().scheduleJob(jobDetail, trigger);
		log.info("My job will start at {}", nextTriggerTime);
	}

}

@Slf4j
class ExampleJob extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		Long data = context.getJobDetail().getJobDataMap().getLong("someData");
		log.info("this job is scheduled at {}", data);

		log.info("DYNAMIC SCHEDULED JOB EXECUTED.");
	}
}