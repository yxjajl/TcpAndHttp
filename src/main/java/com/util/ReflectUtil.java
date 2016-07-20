package com.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.Validate;

public class ReflectUtil {

	public static void printAllField(Object obj) throws Exception {
		List<Field> fields = getAllFieldsList(obj.getClass());
		for (Field field : fields) {
			field.setAccessible(true);
			System.out.println(field.getName() + "=" + field.get(obj));
		}
	}

	public static Map<String, Object> beanToMap(Object obj) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Field> fields = getAllFieldsList(obj.getClass());
		for (Field field : fields) {
			field.setAccessible(true);
			map.put(field.getName(), field.get(obj));
		}
		return map;
	}

	public static void setFieldsValue(Object target, String fieldName, Object value) throws Exception {
		Field field = getField(target.getClass(), fieldName);
		Validate.notNull(field);
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}
		field.set(target, value);
	}

	public static Object getFieldsValue(Object target, String fieldName) throws Exception {
		Field field = getField(target.getClass(), fieldName);
		Validate.notNull(field);
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}
		return field.get(target);
	}

	public static Field getField(final Class<?> clazz, String fieldName) {
		Class<?> currentClass = clazz;
		while (currentClass != null) {
			final Field[] declaredFields = currentClass.getDeclaredFields();
			for (final Field field : declaredFields) {
				if (field.getName().equals(fieldName)) {
					return field;
				}
			}
			currentClass = currentClass.getSuperclass();
		}
		return null;
	}

	public static List<Field> getAllFieldsList(final Class<?> cls) {
		// Validate.isTrue(cls != null, "The class must not be null");
		final List<Field> allFields = new ArrayList<Field>();
		Class<?> currentClass = cls;
		while (currentClass != null) {
			final Field[] declaredFields = currentClass.getDeclaredFields();
			for (final Field field : declaredFields) {
				allFields.add(field);
			}
			currentClass = currentClass.getSuperclass();
		}
		return allFields;
	}

}
