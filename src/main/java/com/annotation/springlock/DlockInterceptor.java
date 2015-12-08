package com.annotation.springlock;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

/**
 * 此类与配置中的 matchBeforeAdvisor 配套成为一个简易aop拦截
 * 
 * @author shuxiaojun
 *
 */
//@Component
// @Aspect
public class DlockInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println(invocation.getMethod().getName());
		Dlock dlock = invocation.getMethod().getAnnotation(Dlock.class);
		// Dlock dlock = AnnotationUtils.findAnnotation(invocation.getMethod(),
		// Dlock.class);

		try {
			Thread current = Thread.currentThread();

			synchronized (dlock.value()) {
				System.out.println(current.getName() + " start lock " + dlock.value());
				return invocation.proceed();
			}
		} catch (Throwable e) {
			throw e;
		} finally {
			System.out.println(Thread.currentThread().getName() + " end lock " + dlock.value());
		}

	}

}