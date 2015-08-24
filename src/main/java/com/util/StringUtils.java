package com.util;

import java.util.StringTokenizer;

import com.google.common.base.Splitter;

public class StringUtils {

	public static void main(String[] args) {
		String str = "this, is, a, test,";
		StringTokenizer st = new StringTokenizer(str, ",");
		while (st.hasMoreTokens()) {
			System.out.println("x:" + st.nextToken());
		}

		Splitter.on(' ').trimResults().omitEmptyStrings().splitToList(str);

	}
}
