package com.excel;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.excel.service.ConfigService;

public class TestMain {
	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:com/excel/applicationContext.xml");

		 readExcel(context);
//		printSql(context);
		System.out.println("XXXXXXXXXXXXXX:end:XXXXXXXXXXXXX");

	}

	public static void readExcel(ClassPathXmlApplicationContext context) throws Exception {
		ExcelReader excelReader = (ExcelReader) context.getBean("excelReader");
		excelReader.read();
	}

	public static void printSql(ClassPathXmlApplicationContext context) throws Exception {
		String sql = "UPDATE job SET industry = %d WHERE category_code = '%d' OR  category_code LIKE  '%d %%'  ;";
		ConfigService cs = (ConfigService) context.getBean("configService");

		cs.getIndustry2().forEach(industry -> {
			cs.getCategory2(industry).forEach(category -> {
				System.out.println(String.format(sql, industry, category, category));
			});
		});

	}
}
