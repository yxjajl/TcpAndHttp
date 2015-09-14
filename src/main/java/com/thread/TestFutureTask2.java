package com.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestFutureTask2 {
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
		Future<String> task = exec.submit(runnable, "aa");

		System.out.println("Let's do important things.");
		// 如果线程内部的事情还未处理完，即还没有返回结果，则会阻塞
		boolean isDone = task.isDone();
		while (!isDone) {
			// Causes the currently executing thread to sleep (temporarily cease
			// execution) for the specified number of milliseconds,
			Thread.sleep(500);
			isDone = task.isDone();
		}
		String obj = task.get();
		System.out.println(obj);// 输出aa，即上面submit方法传入的参数
		// 关闭线程池
		exec.shutdown();
	}
}
