package com.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringUtils {

	public static void main(String[] args) {
		String str = "this, is, a, test,";
		StringTokenizer st = new StringTokenizer(str, ",");
		while (st.hasMoreTokens()) {
			System.out.println("x:" + st.nextToken());
		}

		int[] arr = { 1, 2, 3, 4, 5, 6 };
		System.out.println(Arrays.stream(arr).mapToObj(n -> String.valueOf(n)).collect(Collectors.joining(",", "[", "]")));

		IntStream.iterate(1, i -> i + 1).limit(10).forEach(System.out::println);
		IntStream.range(1, 10).forEach(System.out::println);
		List<Integer> list = IntStream.range(1, 10).boxed().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
	}
}
