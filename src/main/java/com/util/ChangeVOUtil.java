package com.util;

import java.io.File;
import java.util.List;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;

public class ChangeVOUtil {
	public static void main(String[] args) throws Exception {
		List<String> list = FileUtils.readLines(new File("C:\\Users\\r6yuxx\\Desktop\\ss.txt"));

		Function<String, String> mapper = (a) -> a.replaceAll("private [a-zA-Z]+ ", "").replaceAll(";.*", "").replaceAll(" ", "").replaceAll("\t", "");

		String start = "epayCoreReportVO.set";

		list.stream().filter(s -> !s.isEmpty()).map(mapper).forEach(s -> {
			String tt = start + s.substring(0, 1).toUpperCase() + s.substring(1) + "();";
			System.out.println("\""+s+"\",");
		}

		);

	}

}
