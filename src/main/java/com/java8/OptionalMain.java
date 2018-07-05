package com.java8;

import java.util.Optional;

import org.springframework.util.Assert;

import com.excep.BusinessException;

public class OptionalMain {

	public static void main(String[] args) throws Exception {

		Optional<String> tmp = Optional.ofNullable(null);

		System.out.println("null:" + tmp.orElse("3"));

		Assert.isNull(tmp.orElse(null));
		Assert.notNull(tmp.orElse("3"));
		Assert.isTrue(tmp.equals(Optional.empty()));
		Assert.isTrue(!tmp.isPresent());

		Assert.isTrue("none".equals(tmp.orElse("none")));
		Assert.isTrue("none".equals(tmp.orElseGet(() -> "none")));

//		System.out.println("orElseGet= " + tmp.orElseThrow(BusinessException::new));
		System.out.println("map=" + tmp.map((value) -> value.toUpperCase()));
		System.out.println("flatMap=" + tmp.flatMap((value) -> Optional.of(value.toUpperCase())));
		System.out.println("filter=" + tmp.filter((value) -> value.length() > 3));

		tmp.ifPresent(System.out::println);
		System.out.println("=======================================================");
		tmp = Optional.ofNullable("hello");
		System.out.println("test2= " + tmp.get() + "," + tmp.isPresent());
		System.out.println("orElseGet111= " + tmp.orElseGet(() -> "none"));
		System.out.println("orElseGet222= " + tmp.orElseThrow(BusinessException::new));
		System.out.println("map=" + tmp.map((value) -> value.toUpperCase()));
		System.out.println("flatMap=" + tmp.flatMap((value) -> Optional.of(value.toUpperCase())));
		System.out.println("filter=" + tmp.filter((value) -> value.length() > 3));//hello
		System.out.println("filter=" + tmp.filter((value) -> value.length() > 8));//empty
		tmp.ifPresent(System.out::println);

		System.out.println("====================000000000===================================");
		Optional<SubOption> optional1 = Optional.ofNullable(getOp1());
		Optional<SubOption> optional2 = Optional.ofNullable(getOp2());

		optional1.ifPresent((t) -> t.say());
		optional2.ifPresent((t) -> t.say());
		// optional1.orElse(optional2.get()).say();

		// if(optional.isPresent()) {
		// optional.get().say();
		// }

		// so.say();
	}

	public static SubOption getOp1() {
		return null;
	}

	public static SubOption getOp2() {
		return new SubOption();
	}

}

class SubOption {
	public void say() {
		System.out.println("hi ");
	}
}
