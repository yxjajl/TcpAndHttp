package com.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest {

	// ģ����100�����ܣ�10��ѡ���Ѿ�׼��������ֻ�Ȳ���һ�����¡��������˶������յ�ʱ������������
	public static void main(String[] args) throws InterruptedException {

		// �ȴ�10��ѡ�ִֵ�
		final CountDownLatch begin = new CountDownLatch(10);

		// ��Ϸ����
		final CountDownLatch end = new CountDownLatch(10);

		// ��Ϸ��ʼ������ǹ
		final CountDownLatch falin = new CountDownLatch(1);

		// ʮ��ѡ��
		final ExecutorService exec = Executors.newFixedThreadPool(10);

		for (int index = 0; index < 10; index++) {
			final int NO = index + 1;
			Runnable run = new Runnable() {
				public void run() {
					try {
						// �����ǰ����Ϊ�㣬��˷����������ء�
						// �ȴ�
						System.out.println("No." + NO + " arrived1");
						Thread.sleep((long) (Math.random() * 3000));
						begin.countDown();

						falin.await();
//						begin.await();
						System.out.println("No." + NO + " arrived2");
					} catch (InterruptedException e) {
					} finally {
						// ÿ��ѡ�ֵ����յ�ʱ��end�ͼ�һ
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
		// begin��һ����ʼ��Ϸ ����Сֵʼ����0��
		begin.countDown();
		System.out.println("begin.getCount()" + begin.getCount());

		// �ȴ�end��Ϊ0��������ѡ�ֵ����յ�

		System.out.println("Game Over");
		exec.shutdown();
	}
}