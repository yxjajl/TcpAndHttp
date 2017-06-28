package com.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.bag.SynchronizedSortedBag;

public class CompletableFutureTest {

	public static void main(String[] args) throws Exception {
		test2();
	}

	public static void test1() throws InterruptedException, ExecutionException {
		// 以Async结尾并且没有指定Executor的方法会使用ForkJoinPool.commonPool()作为它的线程池执行异步代码
		CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
			pause(2);
			return 100 / 0;
		});

		future = future.whenCompleteAsync((i, t) -> System.out.println(i));
		// 当异常时这个会起作用
		future = future.exceptionally((t) -> {
			System.out.println("发生异常了" + t);
			return 1;
		});

		System.out.println("end");
		System.out.println("get:" + future.get());

		pause(5);
	}

	public static void test2() throws Exception {
		CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
			pause(2);
			return 100 / 1;
		});
		// 兼顾了 whenCompleteAsync 和转换和异常处理
		CompletableFuture<String> future2 = future.handleAsync((i, t) -> {
			if (i != null) {
				return "300";
			}

			if (t != null) {
				System.out.println("异常发生了" + t);
			}
			return "3";
		});

		future2.thenAcceptAsync(System.out::println);

		future = future2.thenApplyAsync(s -> Integer.parseInt(s));

		System.out.println("end");
		System.out.println("get:" + future.get());

		pause(5);
	}

	public static void pause(int sec) {
		try {
			TimeUnit.SECONDS.sleep(sec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
