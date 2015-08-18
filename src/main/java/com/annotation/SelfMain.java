package com.annotation;

import com.aop.JDKProxyFactory;

public class SelfMain {

	public static void main(String[] args) throws Exception {

		// JDKProxyFactory proxyFactory = new JDKProxyFactory();
		CGlibProxyFactory proxyFactory = new CGlibProxyFactory();
		SelfService selfService = (SelfService) proxyFactory.createProxyInstance(new SelfServiceImp());

		new Thread(() -> {
			selfService.syncMethod();
		}).start();

		// Thread.sleep(2000L);
		new Thread(() -> {
			selfService.syncMethod();
		}).start();
	}
}
