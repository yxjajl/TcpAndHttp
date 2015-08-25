package com.threadpool;

public class ThreadJoinTest {

	public static void main(String[] args) throws Exception {
		MyThread mt = new MyThread();
		mt.start();
		mt.join();
	}
}

class MyThread extends Thread {
	public void run() {
		System.out.println();
	}
}
