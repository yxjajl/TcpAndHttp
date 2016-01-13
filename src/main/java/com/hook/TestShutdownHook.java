package com.hook;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestShutdownHook {
	private static volatile boolean running = true;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(2);
		Runnable t1 = new Runnable() {
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println("thread1..." + i);
					try {
						TimeUnit.SECONDS.sleep(1L);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
		};
		Runnable t2 = new Runnable() {
			public void run() {
				for (int i = 0; i < 10; i++) {
					System.out.println("thread2..." + i);
					try {
						TimeUnit.SECONDS.sleep(1L);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
		};
		Thread shutdownThread = new Thread() {
			public void run() {
				service.shutdownNow();
				System.out.println("service.isShutdown() = " + service.isTerminated());
				while (!service.isShutdown()) {
					try {
						Thread.sleep(5000L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("shutdownThread...");
				// synchronized (TestShutdownHook.class) {
				// running = false;
				// TestHook.class.notify();
				// }

			}
		};
		Runtime.getRuntime().addShutdownHook(shutdownThread);
		System.out.println("add...");
		service.submit(t1);
		service.submit(t2);

		try {
			System.in.read();
		} catch (Exception e) {

			e.printStackTrace();
		}
		// service.shutdown();
		System.out.println("over");
		System.exit(-1);

	}
}