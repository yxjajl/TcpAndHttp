package com.function;

public class Test2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Object sum = sum(new Object[] { 0, 1, 2, 3, 4 });
		Object str = join(new Object[] { 0, 1, 2, 3, 4 });
		System.out.println(sum);// output 10
		System.out.println(str);// output 01234
	}

	private static Object join(Object[] objects) {
		return reduce(objects, "", new Func() {
			@Override
			public Object invoke(Object... o) {
				return o[0].toString() + o[1];
			}
		});
	}

	public static Object sum(Object[] arr) {
		return reduce(arr, 0, new Func() {
			@Override
			public Object invoke(Object... o) {
				return (Integer) o[0] + (Integer) o[1];
			}
		});
	}

	private static Object reduce(Object[] arr, Object init, Func f) {
		Object sum = init;
		for (int i = 0; i < arr.length; i++) {
			sum = f.invoke(sum, arr[i]);
		}
		return sum;
	}
}