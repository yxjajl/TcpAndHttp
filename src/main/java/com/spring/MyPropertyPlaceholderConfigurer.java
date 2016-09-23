package com.spring;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

public class MyPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	private static final Logger logger = Logger.getLogger(MyPropertyPlaceholderConfigurer.class);
	private String path;

	public void setPath(String path) {
		System.out.println("====配置文件地址====="+path);
		this.path = path;
		TestPro.url=(path);
		logger.info("====配置文件地址====="+path);
		String str = this.getClass().getClassLoader().getResource("").getPath();
		System.out.println("str="+str);
		//this.setLocation(new ClassPathResource(str+path+"*.properties"));
		//this.setLocation(new FileSystemResource(str+path+"*.properties"));
		
	}

	public String getPath() {
		return path;
	}
	
	
}
