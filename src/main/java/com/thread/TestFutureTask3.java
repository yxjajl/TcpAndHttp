package com.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestFutureTask3 {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		final ExecutorService exec = Executors.newFixedThreadPool(5);
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(1000 * 2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}// 休眠指定的时间，此处表示该操作比较耗时
			}
		};
		Future<?> task = exec.submit(runnable);

		System.out.println("Let's do important things.");
		// 不重要的事情
		// 如果线程内部的事情还未处理完，即还没有返回结果，则会阻塞
		boolean isDone = task.isDone();
		while (!isDone) {
			// Causes the currently executing thread to sleep (temporarily cease
			// execution) for the specified number of milliseconds,
			Thread.sleep(500);
			isDone = task.isDone();
		}
		String obj = (String) task.get();
		System.out.println(obj);// 输出null
		// 关闭线程池
		exec.shutdown();
	}
}