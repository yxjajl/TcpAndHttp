package com.excel;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {
	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:com/excel/applicationContext.xml");
		ExcelReader excelReader = (ExcelReader) context.getBean("excelReader");
		excelReader.read();

		System.out.println("XXXXXXXXXXXXXX:end:XXXXXXXXXXXXX");

	}
}
