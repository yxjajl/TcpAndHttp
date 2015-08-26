package com.small;

import java.text.MessageFormat;

public class StringTest {
	public static void main(String[] args) {
		String str = "123a.+ABC456";
		String re = "#7T$/#";
		String key = "ABC";
		key = java.util.regex.Matcher.quoteReplacement(key);
		re = java.util.regex.Matcher.quoteReplacement(re);
		System.out.println(str.replaceAll(key, re));
		
		
		String message = "Hello %s!%s";
		System.out.println(String.format(message, "Rick",""));
		
		String message2 = "Hello {0}!{0}";
		System.out.println(MessageFormat.format(message2, "rick"));
		
	}
}
