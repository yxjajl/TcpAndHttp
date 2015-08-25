package com.function;

import java.util.Arrays;
import java.util.List;

public class ComapreFunMain {

	public static void main(String[] args) {
		TestClass a = new TestaClass();
		TestClass b = new TestbClass();

		List<TestClass> list = Arrays.asList(a, b);


	}

}

abstract class TestClass {

}

class TestaClass extends TestClass {
	public void print() {
		System.out.println("a");
	}

}

class TestbClass extends TestClass {
	public void print() {
		System.out.println("b");
	}

}
