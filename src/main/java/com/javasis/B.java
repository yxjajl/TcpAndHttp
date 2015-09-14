package com.javasis;

import java.util.concurrent.TimeUnit;

//import javassist.ClassPool;
//import javassist.CtClass;
//import javassist.CtMethod;
//import javassist.CtNewMethod;

public class B {
//	private static CtClass ctClass;
//
	public static void main(String[] args) throws Exception {
//		ctClass = ClassPool.getDefault().get("com.javasis.A");
//		String mname = "method";
//		CtMethod mold = ctClass.getDeclaredMethod(mname);
//		String nname = mname + "$impl";
//		mold.setName(nname);
//		CtMethod mnew = CtNewMethod.copy(mold, mname, ctClass, null);
//		StringBuffer body = new StringBuffer();
//		body.append("{\nlong start = System.currentTimeMillis();\n");
//		body.append(nname + "($$);\n");
//		body.append("System.out.println(\"Call to method " + mname + " took \" +\n (System.currentTimeMillis()-start) + " + "\" ms.\");\n");
//
//		body.append("}");
//		System.out.println(body);
//		mnew.setBody(body.toString());
//		ctClass.addMethod(mnew);
//
//		for (CtMethod md : ctClass.getDeclaredMethods()) {
//			System.out.println(md);
//		}
//		A a = (A) ctClass.toClass().newInstance();
//		a.method();
	}
}