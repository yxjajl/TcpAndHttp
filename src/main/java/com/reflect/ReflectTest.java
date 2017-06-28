package com.reflect;

import java.lang.reflect.Method;
import java.util.Date;

public class ReflectTest {

	public static interface Getter {
		Object get(Object obj);
	}

	public static interface Setter {
		void set(Object obj, Object value);
	}

	public static void testInvoke() {
		try {
			// 创建getter调用器，用于调用getTime方法
			Getter getter = Invokers.newInvoker(Getter.class, Date.class, "getTime", null, Long.TYPE);
			// 创建setter调用器，用于调用setTime方法
			Setter setter = Invokers.newInvoker(Setter.class, Date.class, "setTime", new Class<?>[] { Long.TYPE }, null);
			Date date = new Date();
			System.out.println("time=" + getter.get(date));
			setter.set(date, 33333333L);
			System.out.println("time1=" + getter.get(date));
			Method getTime = Date.class.getMethod("getTime");
			Method setTime = Date.class.getMethod("setTime", Long.TYPE);
			getTime.setAccessible(true);
			setTime.setAccessible(true);
			System.out.println("time2=" + getTime.invoke(date));
			long t = System.currentTimeMillis();
			for (int i = 0; i < 100000000; i++) {
				date.setTime(33333333L);
				date.getTime();
			}
			long t1 = System.currentTimeMillis();
			System.out.println("直接调用耗时：" + (t1 - t) + "ms");
			t1 = System.currentTimeMillis();
			for (int i = 0; i < 100000000; i++) {
				setter.set(date, 33333333L);
				getter.get(date);
			}
			long t2 = System.currentTimeMillis();
			System.out.println("Invokers调用耗时：" + (t2 - t1) + "ms");
			t2 = System.currentTimeMillis();
			for (int i = 0; i < 100000000; i++) {
				setTime.invoke(date, 6666666L);
				getTime.invoke(date);
			}
			long t3 = System.currentTimeMillis();
			System.out.println("JDK反射调用耗时：" + (t3 - t2) + "ms");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		testInvoke();
	}

}
