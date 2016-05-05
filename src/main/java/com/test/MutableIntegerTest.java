package com.test;

import java.time.Clock;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import org.apache.commons.lang.mutable.MutableInt;

public class MutableIntegerTest {

	final static String source = "name name name name name , is me";

	final static String[] words = source.split(" ");

	final static int max = 10000000;

	static Consumer<HashMap<String, int[]>> printer = map -> {
		map.forEach((k, v) -> {
			if (v instanceof int[]) {
				int[] a = (int[]) v;
				System.out.println(k + "," + a[0]);
			}
			else {
				System.out.println(k + "," + v.toString());
			}
		});
	};

	// ======average:=======1895
	// ======average:=======1916
	// ======average:=======1745
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
		System.out.println(Clock.systemDefaultZone().millis());
		LocalTime now1 = LocalTime.now();

		LocalTime now2 = LocalTime.now();
		long hoursBetween = ChronoUnit.SECONDS.between(now1, now2);
		System.out.println(hoursBetween); // -3
		testEfficient(words);
		// testEfficient();

	}

	public static void testtest() {
		int len = 10;
		long sum = 0;
		for (int i = 0; i < len; i++) {
			sum += test();
		}
		System.out.println("======average:=======" + sum / len);
	}

	public static long test() {
		long d1 = System.currentTimeMillis();
		for (int i = 0; i < max; i++) {
			testBetter(words);
		}
		long d2 = (System.currentTimeMillis() - d1);
		System.out.println("use:" + d2);
		return d2;
	}

	// 新手级计数器
	public static void testNaive(String[] words) {
		BiFunction<String, Integer, Integer> bf = (k, v) -> v == null ? 1 : v + 1;

		HashMap<String, Integer> counter = new HashMap<>();
		Arrays.stream(words).forEach(w -> counter.compute(w, bf));
	}

	public static void testBetter(String[] words) {
		// System.out.println("=========testBetter============");
		BiFunction<String, MutableInt, MutableInt> bf = (k, v) -> {
			if (v == null) {
				return new MutableInt(1);
			}
			else {
				v.setValue(v.intValue() + 1);
				return v;
			}
		};
		HashMap<String, MutableInt> counter = new HashMap<>();
		Arrays.stream(words).forEach(w -> counter.compute(w, bf));
		// counter.forEach((k, v) -> System.out.println(k + "," + v));
	}

	public static void testEfficient(String[] words) {
		HashMap<String, MutableInt> counter = new HashMap<String, MutableInt>();
		for (String w : words) {
			MutableInt initValue = new MutableInt(1);
			// 利用 HashMap 的put方法弹出旧值的特性
			MutableInt oldValue = counter.put(w, initValue);
			if (oldValue != null) {
				initValue.setValue(oldValue.intValue() + 1);
			}
		}
	}


	public static void testH(String[] words) {
		BiFunction<String, int[], int[]> bf = (k, v) -> {
			if (v == null) {
				return new int[] { 1 };
			}
			else {
				v[0]++;
				return v;
			}
		};
		HashMap<String, int[]> counter = new HashMap<>();
		Arrays.stream(words).forEach(w -> counter.compute(w, bf));
		printer.accept(counter);
	}
}
