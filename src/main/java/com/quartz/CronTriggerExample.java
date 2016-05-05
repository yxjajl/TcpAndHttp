package com.quartz;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

public class CronTriggerExample {
	//动态修改时间
	static Map<Integer, TriggerKey> triggerMap = new HashMap<Integer, TriggerKey>();

	public static void main(String[] args) throws Exception {
		create();
	}

	public static void create() throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();

		JobDetail job = newJob(MyKaoJob.class).withIdentity("job1", "group1").build();
		CronTrigger trigger = newTrigger().withIdentity("trigger1", "group1").withSchedule(cronSchedule("0 55 8 ? * MON-FRI")).build();
		//0/2 * * * * ?

		Date ft = sched.scheduleJob(job, trigger);

		triggerMap.put(1, trigger.getKey());
		System.out.println("ft = " + ft);

		sched.start();

//		Thread.sleep(10000);
//
//		CronTrigger trigger2 = newTrigger().withIdentity("trigger1", "group1").withSchedule(cronSchedule("0/10 * * * * ?")).build();
//		sched.rescheduleJob(triggerMap.get(1), trigger2);
//		
//		Thread.sleep(20000);
//		
//		
//		CronTrigger trigger3 = newTrigger().withIdentity("trigger1", "group1").withSchedule(cronSchedule("0/2 * * * * ?")).build();
//		sched.rescheduleJob(triggerMap.get(1), trigger3);

	}
}
