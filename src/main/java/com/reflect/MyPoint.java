package com.reflect;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public class MyPoint {
	int x, y;

	public void move(int dx, int dy) {
		x += dx;
		y += dy;
		System.out.println("x=" + x + ",y=" + y);
	}

	public static void main(String[] args) throws Exception {
		ClassPool pool = ClassPool.getDefault();
		CtClass clazz = pool.get("MyPoint");
		CtMethod m = clazz.getDeclaredMethod("move");
	}
}
