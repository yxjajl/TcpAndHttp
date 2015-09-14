package com.typetoken;

import com.google.common.reflect.TypeToken;

public class TypeTokenMain {
	public static void main(String[] args) {
		ClassA<String> ss = new ClassA<String>();

		ss.print();
		System.out.println(new IKnowMyType<String>() {
		}.getMyType());
	}
}

class IKnowMyType<T> {
	public TypeToken<T> getMyType() {
		TypeToken tt = new TypeToken<T>(getClass()) {
		};

		return tt;
	}

	public static void print() {
		System.out.println(new IKnowMyType<String>() {
		}.getMyType());
	}
}