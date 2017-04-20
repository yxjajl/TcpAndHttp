package com.java8;

import java.util.function.Consumer;
import java.util.function.Function;

public class FunctionCode {

	@SuppressWarnings("unused")
	public static void test1() {
		Runnable runnable = () -> System.out.println("run");
		// BiConsumer<T,U>
		// BiFunction<T,U,R>
		// BinaryOperator<T> extends BiFunction<T,T,T>
		// BiPredicate<T,U>
		// Consumer<T> ------->accept(T t)
		// Function<T,R> ----->R apply(T t)
		// Predicate<T> ----->test(T t)
		// Supplier<T> ------>T get()
		// UnaryOperator<T> extends Function<T,T>

		Consumer<String> consumer = s -> System.out.println(s);
		Function<String, String> function = s -> s;

	}

}
