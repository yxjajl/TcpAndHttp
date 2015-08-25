package com.lock;

public class TestMain {

	public static void main(String[] args) {
		Business business = new Business();

		TestThread th1 = new TestThread();
		TestThread th2 = new TestThread();
		th1.setName("th111");
		th2.setName("th222");

		th1.setBusiness(business);
		th2.setBusiness(business);

		new Thread(th1).start();
		new Thread(th2).start();
	}

}
