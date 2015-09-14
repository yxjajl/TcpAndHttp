package com.springloaded;

import java.util.concurrent.atomic.AtomicInteger;


public class TestSpringloader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringLoadedVO springLoadedVO = new SpringLoadedVO();
		springLoadedVO.setAge(1);
		new Thread(new MyRunnable(springLoadedVO)).start();
		AtomicInteger ai = new AtomicInteger(2);
		ai.incrementAndGet();
	}

}


