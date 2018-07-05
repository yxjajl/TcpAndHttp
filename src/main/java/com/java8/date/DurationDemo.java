package com.java8.date;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class DurationDemo {
	public static void main(String[] args) {
		test1();
	}

	public static void test1() {

		// 只能计算秒差
		Duration duration = Duration.between(LocalTime.of(19, 29, 0), LocalTime.of(19, 30, 30));
		// 结果90
		System.out.println(duration.getSeconds());

		// 计算年月日的差值)
		Period period = Period.between(LocalDate.of(2015, 2, 13), LocalDate.of(2018, 2, 12));
		// 结果是2,11,30
		System.out.println(period.getYears() + "," + period.getMonths() + "," + period.getDays());

		long diff = ChronoUnit.HOURS.between(LocalDateTime.of(2018, 3, 1, 1, 5), LocalDateTime.of(2018, 3, 4, 2, 10));
		// 结果73
		System.out.println(diff);

		// 结果2
		long dayDiff = ChronoUnit.DAYS.between(LocalDateTime.of(2018, 3, 1, 10, 5), LocalDateTime.of(2018, 3, 4, 2, 10));
		System.out.println(dayDiff);
	}
}
