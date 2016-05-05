package com.test;

import java.util.HashMap;

import com.util.HttpUtil;

public class KaoQin {

	public static void main(String[] args) {
		//kaoqin();
		test();
	}

	public static void test() {
		String url = "http://10.140.5.169:10000/xsimple";
		HashMap<String, String> map = new HashMap<String, String>();
		
		String str = HttpUtil.get(url);
		System.out.println(str);
	}

	public static void kaoqin() {
		System.getProperties().setProperty("http.proxyHost", "10.141.139.112");
		System.getProperties().setProperty("http.proxyPort", "3128");
		String str = HttpUtil.get("http://10.140.2.55:7021/login.aspx");
		//System.out.println(str);

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("user", "SZK160048");
		map.put("password", "Aa123456");

		String url = "http://10.140.2.55:7021/login.aspx?action=login";

		str = HttpUtil.post(url, map);
		System.out.println(str);
	}
}
