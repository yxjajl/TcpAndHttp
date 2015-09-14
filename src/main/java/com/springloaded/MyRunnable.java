package com.springloaded;

import java.util.concurrent.TimeUnit;

public class MyRunnable implements Runnable {
	private SpringLoadedVO springLoadedVO;

	public MyRunnable(SpringLoadedVO springLoadedVO) {
		this.springLoadedVO = springLoadedVO;
	}

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println("======================");
				System.out.println(springLoadedVO.getAge());
				System.out.println(springLoadedVO.computerAge(2));
				springLoadedVO.setAge(springLoadedVO.getAge() + 1);
				TimeUnit.SECONDS.sleep(3);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}