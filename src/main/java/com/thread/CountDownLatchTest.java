package com.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest {

	// ģ����100�����ܣ�10��ѡ���Ѿ�׼��������ֻ�Ȳ���һ�����¡��������˶������յ�ʱ������������
	public static void main(String[] args) throws InterruptedException {

		// ��ʼ�ĵ�����
		final CountDownLatch begin = new CountDownLatch(1);

		// �����ĵ�����
		final CountDownLatch end = new CountDownLatch(10);

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
						begin.await();
						Thread.sleep((long) (Math.random() * 10000));
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
		

		Thread.sleep(10000L);
		System.out.println("Game Start");
		// begin��һ����ʼ��Ϸ ����Сֵ��0��
		begin.countDown();
		begin.countDown();
		begin.countDown();
		System.out.println("begin.getCount()" + begin.getCount());
		// �ȴ�end��Ϊ0��������ѡ�ֵ����յ�
		end.await();
		System.out.println("Game Over");
		exec.shutdown();
	}
}