package com.thread;

public class PrimeThread extends Thread {
	public void run() {
		int n = 0;
		try {
			while (!Thread.currentThread().isInterrupted()) {
				System.out.println("n == " + n++);
				Thread.sleep(500L);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cancel() {
		this.interrupt();
	}

	public static void main(String[] args) throws Exception {
		PrimeThread pt = new PrimeThread();
		pt.start();

		Thread.sleep(4000L);

		pt.cancel();
	}
}