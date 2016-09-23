package com.test;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

public class TT {
	static ArrayList<String> list = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		
		for (int i = 0; i < 1000000; i++) {
			String tmp = StringUtils.leftPad("" + i, 29, "0");
			list.add(tmp);
		}

		Thread.sleep(1000000000L);
	}

}
