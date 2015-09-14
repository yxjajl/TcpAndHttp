package com.springloaded;

public class SpringLoadedVO {
	private int age;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int computerAge(int value) {
		return age * value;
	}
}
