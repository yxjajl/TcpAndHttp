package com.ss;

public class TestCloneVO implements Cloneable {
	public final String value = "abc";

	public String getValue() {
		return value;
	}

	public static void main(String[] args) throws Exception {
		TestCloneVO tc = new TestCloneVO();
		System.out.println(((TestCloneVO) tc.clone()).getValue());
	}

}
