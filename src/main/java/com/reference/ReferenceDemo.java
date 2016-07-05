package com.reference;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.WeakHashMap;

public class ReferenceDemo {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		test1();
	}

	public static void test1() throws Exception {
		WeakHashMap<Object, Object> weakHashMap = new WeakHashMap<Object, Object>();
		Object key = new String("weakHashMap key");
		Object value = new Object();
		weakHashMap.put(key, value);
		for (int i = 0; i < 1000; i++) {
			System.out.println(weakHashMap.containsValue(value));
		}

		key = null;
		System.gc();
		/**
		 * 等待无效 entries 进入 ReferenceQueue 以便下一次调用 getTable 时被清理
		 */
		Thread.sleep(100);

		/**
		 * 一旦没有指向 key 的强引用, WeakHashMap 在 GC 后将自动删除相关的 entry
		 */
		System.out.println(weakHashMap.containsValue(value));
		System.out.println(weakHashMap.size());
	}

}
