package com.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestRunMain {

	public static void main(String[] args) {
		ExecutorService pool = Executors.newSingleThreadExecutor();
		// 将线程放入池中进行执行
		long d1 = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			pool.execute(new TestRunnable(new TestRunVO()));
		}
		System.out.println("use: " + (System.currentTimeMillis() - d1));
		// 关闭线程池
		pool.shutdown();

		long d3 = System.currentTimeMillis();
		TestRunnable tr = new TestRunnable(new TestRunVO());
		for (int i = 0; i < 1000000; i++) {
			tr.getTestRunVO().setName(i + "1");
			tr.run();
		}
		System.out.println("use: " + (System.currentTimeMillis() - d3));

	}
}
