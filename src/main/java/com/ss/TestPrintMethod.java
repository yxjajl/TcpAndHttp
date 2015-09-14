package com.ss;

import java.lang.reflect.Field;
import java.util.Date;

public class TestPrintMethod {

	public static void main(String[] args) throws Exception {
		// Method[] methods = PrintMethod.class.getMethods();
		// for (Method method : methods) {
		// if (method.getName().startsWith("get")) {
		// System.out.println(method.getName());
		// }
		// }

		PrintMethod pm = new PrintMethod();
		pm.setAge(1);
		pm.setBirthday(new Date());
		pm.setName("am");

		pm.toString();
	}

	public static void printField(Class<?> clz, Object obj) throws Exception {
		Field[] fields = clz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			System.out.println(field.getName() + "=" + field.get(obj));
		}

		if (clz.getSuperclass() != null) {
			printField(clz.getSuperclass(), obj);
		}
	}
}
