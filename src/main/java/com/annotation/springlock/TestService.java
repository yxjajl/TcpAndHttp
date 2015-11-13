package com.annotation.springlock;

import org.springframework.stereotype.Component;

@Component
public class TestService {
	private final static String lock = "123";

	@Dlock(lock)
	public void aa(String str) {
		System.out.println("========aa=======" + str);
	}
}
