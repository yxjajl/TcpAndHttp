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
		// 定义线程1
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
		// 定义线程2
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
		// 定义关闭线程
		Thread shutdownThread = new Thread() {
			public void run() {
				service.shutdown();
				System.out.println("shutdownThread...");
				// synchronized (TestShutdownHook.class) {
				// running = false;
				// TestHook.class.notify();
				// }

			}
		};
		// jvm关闭的时候先执行该线程钩子
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
		System.exit(0);

	}
}