package com.function;

import java.math.BigDecimal;
import java.util.Arrays;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Object[] arr = new Object[] { 1, 2, 3, 4, 5 };
		map(arr, new Func() {
			@Override
			public Object invoke(Object... o) {
				return (Integer) o[0] * 2;
			}
		});
		System.out.println(Arrays.toString(arr));// output [2, 4, 6, 8, 10]
	}

	public static void map(Object[] arr, Func function) {
		for (int i = 0; i < arr.length; i++) {
			arr[i] = function.invoke(arr[i]);
		}
		
	}

}