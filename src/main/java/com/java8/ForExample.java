package com.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

import com.google.common.collect.Lists;

public class ForExample {

	static BinaryOperator<Integer> mf = (a, b) -> a * b;

	static BiFunction<Collection<Integer>, BinaryOperator<Integer>, Integer> ttFunction = (c, f) -> {
		// return c.stream().reduce((sum, item) -> sum + item).get();
		return c.stream().reduce((sum, item) -> f.apply(sum, item)).get();
	};

	static BinaryOperator<String> mf2 = (a, b) -> a + b;

	static BiFunction<Collection<String>, BinaryOperator<String>, String> ttFunction2 = (c, f) -> {
		// return c.stream().reduce((sum, item) -> sum + item).get();
		return c.stream().reduce("", (sum, item) -> f.apply(sum, item));
	};

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		System.out.println(ttFunction.apply(list, mf));

		List<String> list2 = Arrays.asList("1", "2", "3", "4", "5");
		System.out.println(ttFunction2.apply(list2, mf2));
		String concat = list2.stream().collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
		System.out.println(concat);
		// List<Integer> ints = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9,
		// 10);
		// System.out.println("ints sum is:" + ints.stream().reduce((sum, item)
		// -> sum + item).get());

		List<Integer> ints = Lists.newArrayList(1, 2, 3, 4, 5);
		// 注意第三个参数Combiner 在并发运行时合并结果集,串行时则没卵用
		System.out.println("ints sum is:" + ints.stream().reduce(0, (sum, item) -> sum + item));
		System.out.println("ints sum is:" + ints.stream().parallel().reduce(0, (sum, item) -> sum + item, (sum, item) -> sum - item));

	}
}
