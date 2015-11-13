package com.quartz;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;

public class SimpleTriggerRunner {

	public static void main(String args[]) {

		try {

			// ①创建一个JobDetail实例，指定SimpleJob

			JobDetail jobDetail = new JobDetailImpl("job1_1", "jGroup1", SimpleJob.class);

			// ②通过SimpleTrigger定义调度规则：马上启动，每2秒运行一次，共运行100次

			SimpleTriggerImpl simpleTrigger = new SimpleTriggerImpl("trigger1_1", "tgroup1");

			simpleTrigger.setStartTime(new Date());

			simpleTrigger.setRepeatInterval(2000);

			simpleTrigger.setRepeatCount(100);

			// ③通过SchedulerFactory获取一个调度器实例

			SchedulerFactory schedulerFactory = new StdSchedulerFactory();

			Scheduler scheduler = schedulerFactory.getScheduler();

			scheduler.scheduleJob(jobDetail, simpleTrigger);// ④ 注册并进行调度

			scheduler.start();// ⑤调度启动

		} catch (Exception e) {

			e.printStackTrace();

		}

	}


}