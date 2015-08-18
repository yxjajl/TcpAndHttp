package com.thread;

public class DeamonMain {

	public Thread th1 = new Thread(() -> {
		for (int i = 0; i < 1000; i++) {
			System.out.println("a");
			try {
				Thread.sleep(5000L);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
	public Thread th2 = new Thread(() -> System.out.println("b"));

	public static void main(String[] args) {
		DeamonMain dm = new DeamonMain();
		dm.th1.setDaemon(true);
		dm.th1.start();
		dm.th2.start();
	}
}
