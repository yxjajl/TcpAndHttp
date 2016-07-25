package com.java8;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class LocalTimeDemo {
	public static void main(String[] args) {
		localDateTime();
	}

	public static void localDate() {
		LocalDate today = LocalDate.now();
		today = LocalDate.of(2016, 12, 20);
		System.out.printf("Year : %d  Month : %d  day : %d t %n", today.getYear(), today.getMonthValue(), today.getDayOfMonth());
		LocalDate otherday = today.plusDays(5);
		System.out.printf("Year : %d  Month : %d  day : %d t %n", otherday.getYear(), otherday.getMonthValue(), otherday.getDayOfMonth());

		System.out.println(Period.between(today, otherday).getDays());
	}

	public static void localDateTime() {
		LocalDateTime dateTime = LocalDateTime.now();
		LocalDateTime otherDateTime = dateTime.plusHours(9);
		System.out.println(Duration.between(dateTime, otherDateTime).get(ChronoUnit.MINUTES));
	}
}
