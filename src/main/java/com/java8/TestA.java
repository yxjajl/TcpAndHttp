package com.java8;

public class TestA {
	public static void main(String[] args) {
		Thread th = new Thread(() -> System.out.println("abc"));
		new TestA().aa(x -> System.out.println(x.getName()), th);
	}

	private void aa(ThreadInter ti, Thread th) {
		ti.print(th);
	}
}
