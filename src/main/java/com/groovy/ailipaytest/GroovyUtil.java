package com.groovy.ailipaytest;

import java.io.File;

import org.springframework.util.ClassUtils;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyCodeSource;

public class GroovyUtil {

	/**
	 * 加载Groovy类
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static Object loadGroovyFile(File file) throws Exception {
		Class<?> clzz;
		GroovyClassLoader classLoader = new GroovyClassLoader(ClassUtils.getDefaultClassLoader());

		try {
			clzz = classLoader.parseClass(new GroovyCodeSource(file));
			Object obj = clzz.newInstance();
			if (obj == null) {
				System.err.println("obj   errrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
			}
			return obj;
		} catch (Exception e) {
			throw e;
		}
	}
}
