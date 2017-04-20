package com.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TT {
	static ArrayList<String> list = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		System.out.println(tableSizeFor(9));
	}

	static final int MAXIMUM_CAPACITY = 1 << 30;

	static final int tableSizeFor(int cap) {
		int n = cap - 1;
		System.out.println(Integer.toBinaryString(n));
		n |= n >>> 1;
		System.out.println(Integer.toBinaryString(n));
		n |= n >>> 2;
		System.out.println(Integer.toBinaryString(n));
		n |= n >>> 4;
		System.out.println(Integer.toBinaryString(n));
		n |= n >>> 8;
		System.out.println(Integer.toBinaryString(n));
		n |= n >>> 16;
		System.out.println(Integer.toBinaryString(n));
		return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
	}

	public static long test1() {
		long d1 = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			testf(5);
		}
		return (System.currentTimeMillis() - d1);
	}

	public static String testf(int n) {
		switch (n) {
		case 1:
		case -2:
		case 3000:
		case 4:
		case -70000:
		case 9:
		case -10000:
		case 13:
		case -23:
		case 34:
		case 45000:
		case 47:
		case -49000:
		case 55:
		case -57:
		case 59000:
		case 61:
		case -63000:
		case 69:
			return "2";
		case 5:
			return "5";

		}

		return "6";
	}

	static Map<Integer, String> MMAP = new HashMap<Integer, String>();

	static {
		for (int i = 1; i <= 5; i++) {
			MMAP.put(i, "" + i);
		}
	}

	public static String test2(int n) {
		return MMAP.get(n);
	}

	public static String format(java.util.Date date) {
		return String.format("%1$tF %1$tT", date);
//		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

}
