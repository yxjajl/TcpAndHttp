package com.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 以Async结尾的方法都是可以异步执行的
 * 默认会在ForkJoinPool.commonPool()中执行
 * 1.变化
 * public <U> CompletionStage<U> thenApply(Function<? super T,? extends U> fn);
 * public <U> CompletionStage<U> thenApplyAsync(Function<? super T,? extends U> fn);
 * public <U> CompletionStage<U> thenApplyAsync(Function<? super T,? extends U> fn,Executor executor);
 * 2.消耗
 * public CompletionStage<Void> thenAccept(Consumer<? super T> action);
 * public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action);
 * public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action,Executor executor);
 * 
 * 3.对上一步的计算结果不关心，执行下一个操作
 * public CompletionStage<Void> thenRun(Runnable action);
 * public CompletionStage<Void> thenRunAsync(Runnable action);
 * public CompletionStage<Void> thenRunAsync(Runnable action,Executor executor);
 * 
 * 4.结合两个CompletionStage的结果，进行转化后返回
 * public <U,V> CompletionStage<V> thenCombine(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn);
 * public <U,V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn);
 * public <U,V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn,Executor executor);
 * 
 * 5.结合两个CompletionStage的结果，进行消耗
 * public <U> CompletionStage<Void> thenAcceptBoth(CompletionStage<? extends U> other,BiConsumer<? super T, ? super U> action);
 * public <U> CompletionStage<Void> thenAcceptBothAsync(CompletionStage<? extends U> other,BiConsumer<? super T, ? super U> action);
 * public <U> CompletionStage<Void> thenAcceptBothAsync(CompletionStage<? extends U> other,BiConsumer<? super T, ? super U> action, Executor executor);
 * 
 * 6.在两个CompletionStage都运行完执行
 * public CompletionStage<Void> runAfterBoth(CompletionStage<?> other,Runnable action);
 * public CompletionStage<Void> runAfterBothAsync(CompletionStage<?> other,Runnable action);
 * public CompletionStage<Void> runAfterBothAsync(CompletionStage<?> other,Runnable action,Executor executor);
 * 
 * 6.两个CompletionStage，谁计算的快，我就用那个CompletionStage的结果进行下一步的转化操作。
 * public <U> CompletionStage<U> applyToEither(CompletionStage<? extends T> other,Function<? super T, U> fn);
 * public <U> CompletionStage<U> applyToEitherAsync(CompletionStage<? extends T> other,Function<? super T, U> fn);
 * public <U> CompletionStage<U> applyToEitherAsync(CompletionStage<? extends T> other,Function<? super T, U> fn,Executor executor);
 * 
 * 7.两个CompletionStage，谁计算的快，我就用那个CompletionStage的结果进行下一步的消耗操作。
 * public CompletionStage<Void> acceptEither(CompletionStage<? extends T> other,Consumer<? super T> action);
 * public CompletionStage<Void> acceptEitherAsync(CompletionStage<? extends T> other,Consumer<? super T> action);
 * public CompletionStage<Void> acceptEitherAsync(CompletionStage<? extends T> other,Consumer<? super T> action,Executor executor);
 * 
 * 7.两个CompletionStage，任何一个完成了都会执行下一步的操作（Runnable）
 * public CompletionStage<Void> runAfterEither(CompletionStage<?> other,Runnable action);
 * public CompletionStage<Void> runAfterEitherAsync(CompletionStage<?> other,Runnable action);
 * public CompletionStage<Void> runAfterEitherAsync(CompletionStage<?> other,Runnable action,Executor executor);
 * 
 * 8.当运行时出现了异常，可以通过exceptionally进行补偿
 * public CompletionStage<T> exceptionally(Function<Throwable, ? extends T> fn);
 * 
 * 9.当运行完成时，对结果的记录
 * public CompletionStage<T> whenComplete(BiConsumer<? super T, ? super Throwable> action);
 * public CompletionStage<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action);
 * public CompletionStage<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action,Executor executor);
 * 
 * 10.运行完成时，对结果的处理
 * 
 * public <U> CompletionStage<U> handle(BiFunction<? super T, Throwable, ? extends U> fn);
 * public <U> CompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn);
 * public <U> CompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn,Executor executor);
 * 
 * 11.future.get()在等待执行结果时，程序会一直block，如果此时调用complete(T t)会立即执行
 * future.complete();
 * 12. completeExceptionally立即抛出异常
 * completeExceptionally
 * 
 * 13.join 和 get 总体来说是一样的(好像没有发现有什么差别,以后再试)
 * 
 * @author r6yuxx
 *
 */
public class CompletableFutureTest {

	public static void main(String[] args) throws Exception {
		test45();
	}

	public static void test45() throws Exception {
		// 生成器
		CompletableFuture<String> resultCompletableFuture = CompletableFuture.supplyAsync(new Supplier<String>() {

			@Override
			public String get() {
				System.out.println("run--");
				try {
					TimeUnit.SECONDS.sleep(3);
					System.out.println("run xx--" + Thread.currentThread().getName());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return "hello";
			}
		});
		// 如果生成过程中异常。。。。
		resultCompletableFuture.exceptionally(new Function<Throwable, String>() {
			@Override
			public String apply(Throwable t) {
				System.out.println("exceptionally:" + t.getMessage());
				return t.getMessage();
			}
		})
				// 消费 结果
				.thenAccept(new Consumer<String>() {
					@Override
					public void accept(String t) {
						System.out.println("accept--" + Thread.currentThread().getName() + "," + t);
					}
				});
//		resultCompletableFuture.complete("abc"); //立即返回abc, 不等get完成
		resultCompletableFuture.completeExceptionally(new Exception("error")); // 立即抛出异常，不等get 完成
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
		resultCompletableFuture.thenAcceptAsync(i -> System.out.println("thenAcc: " + i + Thread.currentThread().getName()));
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
			return 100 / 0;
		});
		// 兼顾了 whenCompleteAsync 和转换和异常处理
		CompletableFuture<String> future2 = future.handleAsync((i, t) -> {
			System.out.println(i + "," + t); // 如果没有异常 i = 100,t= null,异常的话 i=null t=异常java.util.concurrent.CompletionException
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
