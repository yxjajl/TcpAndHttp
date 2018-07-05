package com.hibernatevalidator;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.hibernatevalidator.subcar.RentalCar;

public class TestMain {

	public static void main(String[] args) throws Exception {
//		byte[] content = FileUtils
//				.readFileToByteArray(new File("C:\\Users\\r6yuxx\\git\\TcpAndHttp\\src\\main\\java\\com\\hibernatevalidator\\constraints-car2.xml"));
//
//		RentalCar rc = new RentalCar("a", "12345", 10);
//		rc.setPrem(new BigDecimal("1.01"));
//		String sss = ValidatorUtil.validator(rc, content);
//		System.out.println("result=\n" + sss);
		test();
	}

	public static void test() {
		List<String> list = Arrays.asList("123", "453333634", "8888", "abch", "sdfhrthj", "mvkd");
		list.stream().forEach(tt -> {

			try {
				if ("8888".equals(tt)) {
					return;
				}
				System.out.println(tt);
			} catch (Exception e) {

			}
		});
	}

}
