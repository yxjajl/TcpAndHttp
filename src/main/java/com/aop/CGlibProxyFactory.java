package com.aop;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CGlibProxyFactory implements MethodInterceptor {
	private Object target;

	public Object createProxyInstance(Object target) {
		this.target = target;
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(this.target.getClass());// 非final
		enhancer.setCallback(this);
		enhancer.setClassLoader(this.target.getClass().getClassLoader());
		return enhancer.create();
	}

	public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		Object result = null;
		// PersonServiceBean bean = (PersonServiceBean) this.target;
		// if (bean.getUser() != null) {
		// 前置通知
		doBefore();
		try {
			result = methodProxy.invoke(target, args);
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