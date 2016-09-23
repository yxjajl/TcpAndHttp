package com.spring;

import java.io.FileInputStream;
import java.net.URL;
import java.security.ProtectionDomain;
import java.util.Properties;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.UrlResource;

public class SpringTest {

	public static void main(String[] args) throws Exception {
		test2();
	}

	public static void test1() {
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:com/spring/applicationContext.xml");

		// MyPropertyPlaceholderConfigurer tt = (MyPropertyPlaceholderConfigurer) context.getBean("propertyConfigurer");
		TestPro testPro = (TestPro) context.getBean("testPro");

		System.out.println("testPro=" + testPro.getAaa());
		System.out.println("testProurl=" + testPro.url);

		System.out.println("XXXXXXXXXXXXXX:end:XXXXXXXXXXXXX");
	}

	public static void test2() throws Exception {
//		String msgToPrint = Thread.currentThread().getStackTrace()[2]
//                .getMethodName();
//		System.out.println(msgToPrint);
//		String threadurl = Thread.currentThread().getContextClassLoader().getResource("").getPath();
//		System.out.println(threadurl);

//		String url = SpringTest.class.getClassLoader().getResource("").getPath(); //classpath
//		String url = SpringTest.class.getProtectionDomain().getCodeSource().getLocation().getPath();//classpath
		String url = System.getProperty("user.dir"); // 工程路径
		System.out.println("url==" + url);
		URL xxx = new URL("file:");
		url = new UrlResource(xxx).getFile().getAbsoluteFile().getAbsolutePath();// 工程路径
		System.out.println("x:"+url);
//		
		Properties p = new Properties();
		p.load(new FileInputStream(url + "/abc.properties"));
		System.out.println(p.get("aaa"));
	}
}
