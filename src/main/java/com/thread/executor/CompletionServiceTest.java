package com.thread.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletionServiceTest {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService exec = Executors.newFixedThreadPool(10);
		// ����CompletionService
		CompletionService<String> serv = new ExecutorCompletionService<String>(exec);
		int size = 0;
		for (int index = 0; index < 500; index++) {
			final int NO = index;
			// Callable �ӿ������� Runnable
			// Callable<String> downImg = new Callable<String>() {
			// public String call() throws Exception {
			// Thread.sleep((long) (Math.random() * 10000));
			// return "Downloaded Image " + NO;
			// }
			// };
			// �ύҪִ�е�ֵ�������񣬲����ر�ʾ������������� Future�������ʱ�����ܻ���ȡ����ѯ������
			// serv.submit(downImg);

			Runnable downImg = new Runnable() {
				public void run() {
					try {
						Thread.sleep((long) (Math.random() * 10000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};

			serv.submit(downImg, "Downloaded Image " + NO);

			size++;
		}
		Thread.sleep(1000 * 2);
		System.out.println("Show web content");
		for (int index = 0; index < size; index++) {
			// ��ȡ���Ƴ���ʾ��һ������������ Future�����Ŀǰ������������������ȴ���
			Future<String> task = serv.take();
			// ���б�Ҫ���ȴ�������ɣ�Ȼ���ȡ������
			String img = task.get();
			System.out.println("a:" + img);
		}
		System.out.println("End");
		// �ر��̳߳�
		exec.shutdown();
	}
}