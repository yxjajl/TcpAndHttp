package com.aop;

public class AopTest {

	public static void main(String[] args) throws Exception {
		proxyTest();
		// proxyTest2();
	}

	private static void proxyTest() throws Exception {
		JDKProxyFactory jdkProxyFactory = new JDKProxyFactory();
		PersonService personService = (PersonService) jdkProxyFactory.createProxyInstance(new PersonServiceBean("zhangsan"));
		personService.save(null);
		personService.exception();
	}

	private static void proxyTest2() {
		CGlibProxyFactory cglibProxyFactory = new CGlibProxyFactory();
		PersonServiceBean bean = (PersonServiceBean) cglibProxyFactory.createProxyInstance(new PersonServiceBean("lisi"));
		bean.save("a");

	}
}