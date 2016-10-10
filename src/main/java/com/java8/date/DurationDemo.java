package com.java8.date;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class DurationDemo {
	public static void main(String[] args) {
		test1();
	}

	public static void test1() {
		Duration duration = Duration.between(LocalTime.of(18, 30, 0), LocalTime.of(19, 30, 30));
		System.out.println(duration.get(ChronoUnit.SECONDS));

		Period period = Period.between(LocalDate.of(2018, 1, 11), LocalDate.of(2018, 2, 12));
		System.out.println(period.get(ChronoUnit.DAYS));

		duration = Duration.between(LocalDate.of(2018, 1, 11), LocalDate.of(2018, 2, 12));
		System.out.println(duration.toDays());
	}
}
