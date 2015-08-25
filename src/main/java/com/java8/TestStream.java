package com.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class TestStream {
	public static void main(String[] args) {
		// test1();
		// test2();
		test3();
	}

	public static void aa() {
		// @NonNull Object obj = null;
		// obj.toString();
	}

	public static void test1() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 1000000; i++) {
			double d = Math.random() * 1000;
			list.add(d + "");
		}
		long start = System.nanoTime();// 获取系统开始排序的时间点
		int count = (int) ((Stream) list.stream().sequential()).sorted().count();
		long end = System.nanoTime();// 获取系统结束排序的时间点
		long ms = TimeUnit.NANOSECONDS.toMillis(end - start);// 得到串行排序所用的时间
		System.out.println(ms + "ms");
	}

	public static void test2() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 1000000; i++) {
			double d = Math.random() * 1000;
			list.add(d + "");
		}
		long start = System.nanoTime();// 获取系统开始排序的时间点
		int count = (int) ((Stream) list.stream().parallel()).sorted().count();
		long end = System.nanoTime();// 获取系统结束排序的时间点
		long ms = TimeUnit.NANOSECONDS.toMillis(end - start);// 得到并行排序所用的时间
		System.out.println(ms + "ms");
	}

	public static void test3() {

	}

}
