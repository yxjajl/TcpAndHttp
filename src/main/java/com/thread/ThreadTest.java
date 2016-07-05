package com.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ThreadTest implements Runnable {
	public String name;
	public int a = 0;

	public ThreadTest(String _name) {
		name = _name;
	}

	public synchronized void inc() {
		a++;
		System.out.println(a + "," + name);
	}

	public void run() {
		for (int i = 0; i < 35; i++) {
			inc();
		}
	}

	public int getA() {
		return a;
	}

	public static void main(String[] args) throws Exception {

		Thread t1 = new Thread(new ThreadTest("t1"));
		Thread t2 = new Thread(new ThreadTest("t2"));
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println("*************"+(29<<1));

	}
}