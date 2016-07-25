package com.java8;

import java.util.HashMap;
import java.util.function.Supplier;

public class HashMapNewMethod {
	static Supplier<HashMap<String, Integer>> mapSupplier = () -> {
		HashMap<String, Integer> map = new HashMap<>();
		for (int i = 0; i < 3; i++) {
			map.put("" + i, i);
		}
		return map;

	};

	public static void main(String[] args) {
		test1();
		test2();
		test3();
	}

	public static void test1() {
		System.out.println("============test1==往map中放值======类似put===========");
		HashMap<String, Integer> map = mapSupplier.get();
		map.compute("1", (k, v) -> v == null ? 1 : v + 1);
		map.compute("5", (k, v) -> v == null ? 1 : v + 1);
		map.forEach((k, v) -> System.out.println(k + "," + v));
	}

	public static void test2() {
		System.out.println("============test2========如果map中没有key,则放入key和value===========");
		HashMap<String, Integer> map = mapSupplier.get();
		map.computeIfAbsent("1", k -> 20);
		map.computeIfAbsent("5", k -> 30);
		map.forEach((k, v) -> System.out.println(k + "," + v));
	}

	public static void test3() {
		System.out.println("============test3====key存在才改变value===============");
		HashMap<String, Integer> map = mapSupplier.get();
		map.computeIfPresent("1", (k, v) -> v == null ? 1 : v + 1);
		map.computeIfPresent("5", (k, v) -> v == null ? 1 : v + 1);
		map.forEach((k, v) -> System.out.println(k + "," + v));
	}

}
