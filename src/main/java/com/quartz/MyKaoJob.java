package com.quartz;

import java.util.Date;
import java.util.HashMap;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import com.util.HttpUtil;

public class MyKaoJob implements Job {

	public void execute(JobExecutionContext context) throws JobExecutionException {

		// This job simply prints out its job name and the
		// date and time that it is running
		JobKey jobKey = context.getJobDetail().getKey();
		System.out.println("SimpleJob says: " + jobKey + " executing at " + new Date());
		kaoqin();
	}

	public static void kaoqin() {
		String str = HttpUtil.get("http://10.140.2.55:7021/login.aspx");
		//System.out.println(str);

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("user", "SZK160048");
		map.put("password", "Aa123456");

		String url = "http://10.140.2.55:7021/login.aspx?action=login";

		str = HttpUtil.post(url, map);
		System.out.println(str);
	}

}