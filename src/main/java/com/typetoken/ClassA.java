package com.typetoken;

import com.google.common.reflect.TypeToken;

public class ClassA<T> {
	private TypeToken<T> recordType;

	public ClassA() {
		this.recordType = new TypeToken<T>(getClass()) {
		};

	}

	public void print() {
		System.out
				.println(((Class<T>) recordType.getRawType()).getSimpleName());
	}
}
