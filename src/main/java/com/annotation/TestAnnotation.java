package com.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestAnnotation {
	public static void main(String[] args) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		AnnotationDemo aAnnotationDemo = new AnnotationDemo();
		Class<?> cls = AnnotationDemo.class;// Class.forName("com.AnnotationDemo");
		boolean flag = cls.isAnnotationPresent(MyAnnotation1.class);
		if (flag) {
			System.out.println("判断类是annotation");
			MyAnnotation1 annotation1 = cls.getAnnotation(MyAnnotation1.class);
			System.out.println(annotation1.value());
		}
		
		Method[] methods = cls.getMethods();
		for (Method method : methods) {
			if (!method.isAnnotationPresent(MyAnnotation2.class)) {
				continue;
			}
			
			if(method.getAnnotation(MyAnnotation2.class).command() == CommandCode.SAYY) {
				method.invoke(aAnnotationDemo, new Object[0]);
				break;
			}
		}

		Method method = cls.getMethod("sayHello");
		flag = method.isAnnotationPresent(MyAnnotation2.class);
		if (flag) {
			System.out.println("判断方法也是annotation");
			MyAnnotation2 annotation2 = method.getAnnotation(MyAnnotation2.class);
			System.out.println(annotation2.description() + "/t" + annotation2.isAnnotation());
		}
		
		Annotation[] ac = AnnotationDemo.class.getPackage().getAnnotations();
		for(Annotation clz :ac)
		System.out.println("==" + clz.annotationType().getSimpleName());
	}

}