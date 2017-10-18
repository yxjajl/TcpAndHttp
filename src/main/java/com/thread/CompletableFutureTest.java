package com.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class CompletableFutureTest {

	public static void main(String[] args) throws Exception {
		test1();
	}

	public static void test45() throws Exception {
		CompletableFuture<String> resultCompletableFuture = CompletableFuture.supplyAsync(new Supplier<String>() {

			@Override
			public String get() {
				System.out.println("run--");
				try {
					TimeUnit.SECONDS.sleep(3);
					System.out.println("run ??--" + Thread.currentThread().getName());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return "hello";
			}
		}, Executors.newFixedThreadPool(1));
		resultCompletableFuture.exceptionally(new Function<Throwable, String>() {
			@Override
			public String apply(Throwable t) {
				System.out.println("exceptionally:" + t.getMessage());
				return t.getMessage();
			}
		}).thenAccept(new Consumer<String>() {
			@Override
			public void accept(String t) {
				System.out.println("accept--" + Thread.currentThread().getName() + "," + t);
			}
		});
		resultCompletableFuture.completeExceptionally(new Exception("error"));
		System.out.println("over");
	}

	public static void test4() throws Exception {
		CompletableFuture<String> resultCompletableFuture = CompletableFuture.supplyAsync(new Supplier<String>() {

			@Override
			public String get() {
				System.out.println("run--");
				try {
					TimeUnit.SECONDS.sleep(3);
					System.out.println("run ??--" + Thread.currentThread().getName());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return "hello";
			}
		}, Executors.newFixedThreadPool(1));
		resultCompletableFuture.exceptionally(new Function<Throwable, String>() {
			@Override
			public String apply(Throwable t) {
				System.out.println("exceptionally:" + t.getMessage());
				return t.getMessage();
			}
		}).thenAccept(new Consumer<String>() {
			@Override
			public void accept(String t) {
				System.out.println("accept--" + Thread.currentThread().getName() + "," + t);
			}
		});
		resultCompletableFuture.thenAcceptAsync(i -> System.out.println(i + Thread.currentThread().getName()));
		System.out.println("over");
	}

	public static void test1() throws InterruptedException, ExecutionException {
		// 以Async结尾并且没有指定Executor的方法会使用ForkJoinPool.commonPool()作为它的线程池执行异步代码
		CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
			pause(2);
			return 100 / 0;
		});

		// 此处要注意 whenCompleteAsync和exceptionally的位置，(whenCompleteAsync在前打印空,在后则打印1)
		future = future.whenCompleteAsync((i, t) -> System.out.println("x:" + i));
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
