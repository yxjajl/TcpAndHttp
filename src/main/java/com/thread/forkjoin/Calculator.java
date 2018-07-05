package com.thread.forkjoin;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class Calculator extends RecursiveTask<Integer> {
	private static final long serialVersionUID = 1L;
	private static final int THRESHOLD = 300;
	private int start;
	private int end;

	public Calculator(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {

		try {
			int x = new Random().nextInt(5);
			System.out.println("x=" + x);
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int sum = 0;
		if ((end - start) < THRESHOLD) {
			for (int i = start; i < end; i++) {
				sum += i;
			}
		} else {
			int middle = (start + end) / 2;
			System.out.println("middle = " + middle);
			Calculator left = new Calculator(start, middle);
			Calculator right = new Calculator(middle + 1, end);
			left.fork();
			right.fork();

			sum = left.join() + right.join();
		}
		return sum;
	}

	public static void main(String[] args) throws Exception {
		ForkJoinPool forkJoinPool = new ForkJoinPool(4);// 最大并发数4
		Future<Integer> result = forkJoinPool.submit(new Calculator(0, 10000));
//		Assert.assertEquals(new Integer(49995000), result.get());
		System.out.println("result.get() = " + result.get());

//		forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);// 阻塞当前线程直到 ForkJoinPool 中所有的任务都执行结束
		// 关闭线程池
		forkJoinPool.shutdown();
	}

}