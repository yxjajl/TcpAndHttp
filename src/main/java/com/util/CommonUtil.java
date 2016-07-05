package com.util;

import java.lang.reflect.Field;

public class CommonUtil {
	public static String getClassString(Object obj) {
		Class<?> clz = obj.getClass();
		Field[] arr = clz.getDeclaredFields();
		StringBuilder sb = new StringBuilder();

		for (Field field : arr) {
			String value = "";
			String name = field.getName();
			field.setAccessible(true);
			try {
				value = "" + field.get(obj);

			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("no found field " + name);
			}
			sb.append(name).append(":").append(value).append("\n");
		}
		return sb.toString();
	}

	public static void main(String[] args) {
	}
}