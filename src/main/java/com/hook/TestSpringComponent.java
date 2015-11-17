package com.hook;

import org.springframework.stereotype.Component;

@Component
public class TestSpringComponent {
	public TestSpringComponent() {
		System.out.println("TestSpringComponent");
	}
	
	public void say() {
		System.out.println(" heool ");
	}
}
