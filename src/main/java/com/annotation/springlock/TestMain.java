package com.annotation.springlock;

import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {
	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:com/annotation/springlock/applicationContext.xml");

//		TestService testService = (TestService) context.getBean("testService");
//
//		testService.aa("rick");

		Map<String, Object> map = context.getBeansWithAnnotation(OrderQuery.class);
		for(Object obj :map.values()) {
			((AOrderQuery)obj).say();
		}
		

		System.out.println("XXXXXXXXXXXXXX:end:XXXXXXXXXXXXX");

	}

}
