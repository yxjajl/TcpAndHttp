package com.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestS {

	public static void main(String[] args) throws Exception {
		String redirectUrl = "http://www.baidu.com:8085/Fwechat/goNewRegister";
		Pattern p = Pattern.compile("(?<=http://|\\.)[^.]*?\\.(ccom|cn|net|org|biz|info|cc|tv)", Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(redirectUrl);
		matcher.find();
		String domain = matcher.group();
		System.out.println(domain);
	}

}
