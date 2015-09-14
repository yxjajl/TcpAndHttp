package com.ss;

import java.util.function.Consumer;

public class PossibleReordering {
	static int x = 0, y = 0;
	static int a = 0, b = 0;

	public static void main(String[] args) throws InterruptedException {
		Thread one = new Thread(new Runnable() {
			@Override
			public void run() {
				foreach(0, 1000, 1, new Consumer<Integer>() {
					public void accept(Integer value) {
						System.out.println("one:" + value);
						sleep(60);
					}
				});
				// a = 1;
				// x = b;
			}
		});

		Thread other = new Thread(new Runnable() {
			@Override
			public void run() {
				foreach(0, 1000, 1, new Consumer<Integer>() {
					public void accept(Integer value) {
						System.out.println("two:" + value);
						sleep(50);
					}
				});

				// b = 1;
				// y = a;
			}
		});

		one.start();
		other.start();
		one.join();
		other.join();

		System.out.println("x = " + x + ", y = " + y);
		System.out.println("a = " + a + ", b = " + b);
	}

	public static void foreach(int start, int end, int step, Consumer<Integer> consumer) {
		for (int i = start; i < end; i += step) {
			consumer.accept(i);
		}
	}

	public static void sleep(long value) {
		try {
			Thread.sleep(value);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}