package com.thread.executor;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceTest {
	static int n = 0;
	public static void main(String[] args) throws IOException, InterruptedException {
		 ExecutorService service = Executors.newFixedThreadPool(1);
//		ExecutorService service = Executors.newCachedThreadPool();
		 
		for (int i = 0; i < 100; i++) {
			Runnable run = new Runnable() {
				@Override
				public void run() {
					System.out.println("hehe:" + n++);
					try {
						Thread.sleep(10L + new Random().nextInt(10));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			service.execute(run);
		}
		service.shutdown();
		service.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
		System.out.println("all thread complete");
	}
}