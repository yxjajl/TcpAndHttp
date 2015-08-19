package com.aop;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class TowClass {
	public static void main(String[] args) {
		TwoClassA a = new TwoClassA();
		TwoClassB b = new TwoClassB();

		Message message1 = (Message) new HelpImp().create(a);
		Message message2 = (Message) new HelpImp().create(b);
		System.out.println(message1.getMessage("a1"));
		System.out.println(message2.getMessage("a2"));
	}
}

class TwoClassA {
	public String getMessage(String msg) {
		return msg;
	}
}

class TwoClassB {
	public String getMessage(String msg) {
		return msg;
	}
}

interface Message {
	public String getMessage(String msg);
}

class HelpImp implements MethodInterceptor {
	private Object target;

	public Object create(Object target) {
		this.target = target;
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(target.getClass());// 非final
		enhancer.setInterfaces(new Class[] { Message.class });
		enhancer.setCallback(this);
		enhancer.setClassLoader(target.getClass().getClassLoader());
		return enhancer.create();
	}

	public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		Object result = null;
		try {
			result = methodProxy.invoke(target, args);
		} catch (Exception e) {
			throw e;
		} finally {
		}
		return result;
	}
}