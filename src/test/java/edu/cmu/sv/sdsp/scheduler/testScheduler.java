package edu.cmu.sv.sdsp.scheduler;

import java.util.Date;
import java.util.TimeZone;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * This scheduler runs the api tests every day.
 * 
 * @author Gonghan
 * 
 */
public class testScheduler {
	// set interval as one day (60 * 60 * 24 seconds)
	private static final int INTERVAL = 60*6;
	private static final int START_HOUR = 17;
	private static final int START_MINUTE = 18;

	public static void main(String[] args) throws Exception {

		// Set the start time
		java.util.Calendar cal = new java.util.GregorianCalendar();
		cal.setTimeZone(TimeZone.getTimeZone("PST"));
		cal.set(cal.HOUR, START_HOUR - 12);
		cal.set(cal.MINUTE, START_MINUTE);
		cal.set(cal.SECOND, 30);
		cal.set(cal.MILLISECOND, 0);

		Date startTime = cal.getTime();

		System.out.println(startTime.toString());

		JobDetail job = JobBuilder.newJob(DailyTestJob.class)
				.withIdentity("APITest", "UnitTest").build();

		Trigger trigger = TriggerBuilder
				.newTrigger()
				.startAt(startTime)
				.withIdentity("APITestTrigger", "UnitTest")
				.withSchedule(
						SimpleScheduleBuilder.simpleSchedule()
								.withIntervalInSeconds(INTERVAL)
								.repeatForever()).build();
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();
		scheduler.scheduleJob(job, trigger);
	}
}
