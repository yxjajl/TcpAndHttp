package com.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest {

	// 模拟了100米赛跑，10名选手已经准备就绪，只等裁判一声令下。当所有人都到达终点时，比赛结束。
	public static void main(String[] args) throws InterruptedException {

		// 等待10名选手抵达
		final CountDownLatch begin = new CountDownLatch(10);

		// 游戏结束
		final CountDownLatch end = new CountDownLatch(10);

		// 游戏开始，发令枪
		final CountDownLatch falin = new CountDownLatch(1);

		// 十名选手
		final ExecutorService exec = Executors.newFixedThreadPool(10);

		for (int index = 0; index < 10; index++) {
			final int NO = index + 1;
			Runnable run = new Runnable() {
				public void run() {
					try {
						// 如果当前计数为零，则此方法立即返回。
						// 等待
						System.out.println("No." + NO + " arrived1");
						Thread.sleep((long) (Math.random() * 3000));
						begin.countDown();

						falin.await();
//						begin.await();
						System.out.println("No." + NO + " arrived2");
					} catch (InterruptedException e) {
					} finally {
						// 每个选手到达终点时，end就减一
						end.countDown();
					}
				}
			};
			exec.submit(run);
		}

		begin.await();
		falin.countDown();
		System.out.println("Game Start");

		end.await();
		// begin减一，开始游戏 ，最小值始终是0，
		begin.countDown();
		System.out.println("begin.getCount()" + begin.getCount());

		// 等待end变为0，即所有选手到达终点

		System.out.println("Game Over");
		exec.shutdown();
	}
}