package com.annotation;

import java.util.HashMap;
import java.util.Map;

public class Lock {
	private MyLock mLock = new MyLock();
	private Map<String, Object> lockMap = new HashMap<String, Object>();

	private static Lock INS = new Lock();

	private Lock() {
	}

	public static Lock getInstance() {
		return INS;
	}

	// public Object getLock(String key) {
	// System.out.println("lock:" + key);
	// return lockMap.computeIfAbsent(key, k -> new MyLock());
	// }

	public Object getLock(String key) {

		Object obj = lockMap.get(key);
		if (obj == null) {
			synchronized (mLock) {
				obj = lockMap.get(key);
				if (obj == null) {
					obj = new MyLock();
					lockMap.put(key, obj);
				}
			}
		}

		return obj;

		// return lockMap.computeIfAbsent(key, k -> new MyLock());
	}

	public static void main(String[] args) {
		Map<String, String> map1 = new HashMap<>();
		map1.put("s", "s1");
		map1.put("a", "11");
		map1.put("fruits", "22");
		// 没有就插入，有就放弃
		// map1.computeIfAbsent("fruits", k -> "a" + k);
		// 有就替换，没有就不管
		// map1.computeIfPresent("fruits", (k,v) -> "a" + k);
		// map1.computeIfAbsent("fruits", k -> "a" + k);
		// map1.putIfAbsent("fruits", "44");
		System.out.println(map1);

		Lock lock = getInstance();
		lock.getLock("123");
		lock.getLock("123");
		lock.getLock("123");
		lock.getLock("123");
		lock.getLock("123");
	}
}

class MyLock {
	public MyLock() {
		System.out.println("MyLock=========");
	}
}