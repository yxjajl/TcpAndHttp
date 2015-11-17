package com.hook;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestHook {
	private static volatile boolean running = true;

	public static void main(String[] args) {
		long t = System.currentTimeMillis();
		try {

			final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
					"classpath*:com/hook/applicationContext.xml");

			Runtime.getRuntime().addShutdownHook(new Thread() {
				public void run() {
					System.out.println("hook thread");
					try {
						context.stop();
					} catch (Throwable t) {
						System.err.println(t.getMessage());
					}
					// synchronized (TestHook.class) {
					// running = false;
					// TestHook.class.notify();
					// }
				}
			});
			System.out.println("start===");
			context.start();
		} catch (RuntimeException e) {
			e.printStackTrace();
			System.exit(1);
		}

		// synchronized (TestHook.class) {
		// while (running) {
		// try {
		// TestHook.class.wait();
		// } catch (Throwable e) {
		// }
		// }
		// }

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
