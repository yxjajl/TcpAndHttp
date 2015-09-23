package com.util;
public class TimeComp {
	private long start = 0;

	public TimeComp() {
		start = System.currentTimeMillis();
	}

	public long comsum() {
		long now = System.currentTimeMillis();
		long use = now - start;
		start = now;
		return use;
	}
}