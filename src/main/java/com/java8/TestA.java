package com.java8;

import java.math.BigDecimal;

public class TestA {
	public static void main(String[] args) {
		//Thread th = new Thread(() -> System.out.println("abc"));
		//new TestA().aa(x -> System.out.println(x.getName()), th);
		BigDecimal a = new BigDecimal("1");
		BigDecimal b = new BigDecimal("1.000");
		System.out.println(a.compareTo(b));
		System.out.println(a.equals(b));
	}

	private void aa(ThreadInter ti, Thread th) {
		ti.print(th);
	}
}
