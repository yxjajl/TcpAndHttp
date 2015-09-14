package com.ss;

import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class SSTest {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int[] arr = SSTest.getDate();
		for (int value : arr) {
			System.out.print((char) value);
		}
		System.out.println("==========");
		arr = new int[] { 87, 101, 108, 99, 111, 109, 101, 78, 101, 111 };
		for (int value : arr) {
			System.out.print((char) value);
		}

		new HashMap<String, String>();
		new ConcurrentHashMap<>();
		System.out.println();
		SSTest sst = new SSTest();
		// Future<String> future =
		// Executors.newCachedThreadPool().submit(sst.new Mycall());
		// System.out.println(future.isDone());
		// if (future.isDone()) {
		// System.out.println("aa:" + future.get());
		// }
		// System.out.println("==end==");

		FutureTask<String> ft = new FutureTask<String>(sst.new Mycall());
		new Thread(ft).start();
		System.out.println("==end==");
		while (!ft.isDone()) {
			System.out.println("===running...");
			Thread.sleep(1000L);
		}
		System.out.println("ft:" + ft.get());
	}

	public static int[] getDate() {
		int[] src = new int[36];
		int n = 0;
		src[n++] = 0x38;
		src[n++] = 0x37;
		src[n++] = 0x20;
		src[n++] = 0x31;
		src[n++] = 0x30;
		src[n++] = 0x31;
		src[n++] = 0x20;
		src[n++] = 0x31;
		src[n++] = 0x30;
		src[n++] = 0x38;
		src[n++] = 0x20;
		src[n++] = 0x39;
		src[n++] = 0x39;
		src[n++] = 0x20;
		src[n++] = 0x31;
		src[n++] = 0x31;
		src[n++] = 0x31;
		src[n++] = 0x20;
		src[n++] = 0x31;
		src[n++] = 0x30;
		src[n++] = 0x39;
		src[n++] = 0x20;
		src[n++] = 0x31;
		src[n++] = 0x30;
		src[n++] = 0x31;
		src[n++] = 0x20;
		src[n++] = 0x37;
		src[n++] = 0x38;
		src[n++] = 0x20;
		src[n++] = 0x31;
		src[n++] = 0x30;
		src[n++] = 0x31;
		src[n++] = 0x20;
		src[n++] = 0x31;
		src[n++] = 0x31;
		src[n++] = 0x31;
		return src;
	}

	class Mycall implements Callable<String> {

		@Override
		public String call() throws Exception {
			// TODO Auto-generated method stub
			Thread.sleep(5000L);
			return "1";
		}

	}

}
