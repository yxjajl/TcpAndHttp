package com.test;

import org.springframework.util.Assert;

public class ExceptionMain {

	public static void main(String[] args) {
		String sql = null;
		Assert.notNull(sql, "SQL must not be null");
		Integer v = Integer.valueOf("1");
		System.out.println(v);
	}

}
