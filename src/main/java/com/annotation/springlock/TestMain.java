package com.annotation.springlock;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {
	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath*:com/annotation/springlock/applicationContext.xml");

		TestService testService = (TestService) context.getBean("testService");

		testService.aa("rick");

		System.out.println("XXXXXXXXXXXXXX:end:XXXXXXXXXXXXX");

	}

}
