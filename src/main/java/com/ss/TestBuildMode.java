package com.ss;

import java.util.HashMap;

public class TestBuildMode {
	private final int aa;
	private final int bb;
	private final String cc;

	public static class Builder {
		private int a = 0;
		private int b = 0;
		private String c = null;

		public Builder A(int value) {
			a = value;
			return this;
		}

		public Builder B(int value) {
			b = value;
			return this;
		}

		public Builder C(String value) {
			c = value;
			return this;
		}

		public TestBuildMode build() {
			return new TestBuildMode(this);
		}
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	private TestBuildMode(Builder builder) {
		aa = builder.a;
		bb = builder.b;
		cc = builder.c;
	}

	public String toString() {
		return aa + "," + bb + "," + cc;
	}

	public static void main(String[] args) {
		System.out.println(TestBuildMode.newBuilder().A(1).B(2).C("abc").build().toString());
		new HashMap<String,String>();
	}

}
