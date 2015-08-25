package com.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Business {

	public int a = 0;
	public int b = 0;
	Lock lock = new ReentrantLock();

	public void changeValue(String str) throws InterruptedException {
		lock.lock();
		try {
			System.out.println("==================" + str + "=======start================");
			a++;
			System.out.println(str + "=== a= " + a);

			if (str.equals("th111")) {
				Thread.sleep(10000);
			} else {
				Thread.sleep(10);
			}

			b++;
			System.out.println(str + "=== b= " + b);
			System.out.println("==================" + str + "=========end==============");
		} finally {
			lock.unlock();
		}

	}

}
