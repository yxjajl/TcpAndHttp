package com.annotation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxyFactory implements InvocationHandler {
	// 要代理的原始对象
	private Object targer;

	/**
	 * 创建动态代理
	 * 
	 * @param targer
	 *            要代理的原始对象
	 * @return
	 */
	public Object createProxyInstance(Object targer) {
		this.targer = targer;
		return Proxy.newProxyInstance(this.targer.getClass().getClassLoader(), this.targer.getClass().getInterfaces(), this);
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result = null;
		// PersonServiceBean bean = (PersonServiceBean) this.targer;
		// if (bean.getUser() != null) {
		// 前置通知
		doBefore();
		try {
			Self self = method.getAnnotation(Self.class);
			if (self != null) {
				Object obj = Lock.getInstance().getLock(self.value());
				synchronized (obj) {
					result = method.invoke(targer, args);
				}
			}
			// 后置通知
			doAfter();
		} catch (Exception e) {
			// 例外通知
			doException();
		} finally {
			// 最终通知
			doFinally();
		}
		// }
		return result;
	}

	private void doBefore() {
		System.out.println("我是前置通知");
	}

	private void doAfter() {
		System.out.println("我是后置通知");
	}

	private void doException() {
		System.out.println("我是例外通知");
	}

	private void doFinally() {
		System.out.println("我是最终通知");
	}

}