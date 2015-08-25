package com.boundle;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class boundle {
	private static ResourceBundle ALERT_RESOURCE_BUNDLE = null;

	static {
		Locale myLocale = Locale.getDefault();// 获得系统默认的国家/语言环境
		ResourceBundle bundle = ResourceBundle.getBundle("apppro", myLocale);
		ALERT_RESOURCE_BUNDLE = bundle;
	}

	public static String getAlertMsg(String key) {
		return ALERT_RESOURCE_BUNDLE.getString(key);
		// String str = "";
		// try {
		// str = new String(ALERT_RESOURCE_BUNDLE.getString(key).getBytes("ISO8859-1"), "UTF-8");
		// } catch (UnsupportedEncodingException e) {
		// e.printStackTrace();
		// }
		// return str;
	}

	public static void main(String[] args) throws Exception {
		String str = new String(boundle.getAlertMsg("hello").getBytes("ISO8859-1"), "UTF-8");
		System.out.println(str);
		System.out.println(MessageFormat.format(str, "a"));

	}
}
