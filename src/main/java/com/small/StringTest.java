package com.small;

public class StringTest {
	public static void main(String[] args) {
		String str = "123a.+ABC456";
		String re = "#7T$/#";
		String key = "ABC";
		key = java.util.regex.Matcher.quoteReplacement(key);
		re = java.util.regex.Matcher.quoteReplacement(re);
		System.out.println(str.replaceAll(key, re));
	}
}
