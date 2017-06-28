package com.reflect;

public class FB extends FA {

	public static void hello() {
		System.out.println("FB hello");
	}

	public static void main(String[] args) {
		FA.hello();
	}

	public String getMessage(String a) {
		return a + " end";
	}
}
