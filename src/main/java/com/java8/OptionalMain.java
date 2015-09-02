package com.java8;

import java.util.Optional;

public class OptionalMain {

	public static void main(String[] args) {

		Optional<SubOption> optional1 = Optional.ofNullable(getOp1());
		Optional<SubOption> optional2 = Optional.ofNullable(getOp2());

		optional1.orElse(optional2.get()).say();

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
