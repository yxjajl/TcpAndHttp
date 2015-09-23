package com.thread;

import java.util.concurrent.TimeUnit;

public class StopThread {
	private static volatile boolean stopRequested = false;

	public static void main(String[] args) throws Exception {
		Thread back = new Thread(new Runnable() {
			public void run() {
				int i = 0;
				while (!stopRequested) {
					i++;
					System.out.println("i="+i);
				}
			}
		});

		back.start();
		TimeUnit.SECONDS.sleep(1);
		stopRequested = true;
	}
}
