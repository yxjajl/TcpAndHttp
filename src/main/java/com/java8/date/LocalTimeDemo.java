package com.java8.date;

import java.time.LocalTime;

//SQL -> Java  java日期时间类型与sql类型的映射
//--------------------------
//date -> LocalDate
//time -> LocalTime
//timestamp -> LocalDateTime
public class LocalTimeDemo {
	public static void main(String[] args) {
		test1();
	}

	public static void test1() {
		LocalTime now = LocalTime.now(); // 11:09:09.240
		System.out.println(now);
		// 不要毫秒
		now = LocalTime.now().withNano(0);
		System.out.println(now);

		LocalTime zero = LocalTime.of(0, 0, 0); // 00:00:00
		LocalTime mid = LocalTime.parse("12:00:00"); // 12:00:00

		System.out.println("zero:"+zero);
		System.out.println("mid:"+mid);
	}

	public static void test2() {
		LocalTime time = LocalTime.now();
		LocalTime newTime = time.plusHours(2); // adding two hours
		System.out.println("Time after 2 hours : " + newTime);
	}
}
