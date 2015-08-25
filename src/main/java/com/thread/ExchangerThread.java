package com.thread;

import java.util.concurrent.Exchanger;

/**
 * 交换，两个线程之间交换数据
 * @author zhengcandong
 *
 */
public class ExchangerThread {

	public static void main(String[] args) {
		Exchanger<String> ex = new Exchanger<>();
		Thread th1 = new MyThread(ex, "abc");
		Thread th2 = new MyThread(ex, "123");
		th1.start();
		th2.start();
	}
}

class MyThread extends Thread {
	Exchanger<String> ex = new Exchanger<>();
	String str;

	public MyThread(Exchanger<String> ex, String str) {
		super(str);
		this.ex = ex;
		this.str = str;
	}

	public void run() {
		try {
			System.out.println(this.getName() + ex.exchange(str));
		} catch (Exception e) {
		}
	}
}
