package com.quartz;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.JobKey.jobKey;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.quartz.examples.example2.SimpleJob;

public class MyCron {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyCron.class);

	public static void main(String[] args) throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		sched.start();

		JobDetail job = newJob(SimpleJob.class).withIdentity("job8", "group1").storeDurably().build();

		sched.addJob(job, true);

		LOGGER.info("'Manually' triggering job8...");
		sched.triggerJob(jobKey("job8", "group1"));

		LOGGER.info("------- Waiting 30 seconds... --------------");
	}

}
