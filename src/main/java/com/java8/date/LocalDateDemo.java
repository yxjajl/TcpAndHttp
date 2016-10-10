package com.java8.date;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.MonthDay;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalQueries;

public class LocalDateDemo {
	public static void main(String[] args) {
		test4();
	}

	public static void test1() {
		LocalDate today = LocalDate.now();
		LocalDate x = LocalDate.of(2016, 3, 31);
		LocalDate endOfFeb = LocalDate.parse("2016-03-31");

		// 取本月第1天：
		LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth()); // 2014-12-01
		// 取本月第2天：
		LocalDate secondDayOfThisMonth = today.withDayOfMonth(2); // 2014-12-02
		// 取本月最后一天，再也不用计算是28，29，30还是31：
		LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth()); // 2014-12-31
		// 取下一天：
		LocalDate firstDayOf2015 = lastDayOfThisMonth.plusDays(1); // 变成了2015-01-01

		// 取2015年1月第一个周一，这个计算用Calendar要死掉很多脑细胞：
		LocalDate firstMondayOf2015 = LocalDate.parse("2015-01-01").with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)); // 2015-01-05

	}

	// 生日判断
	public static void test2() {
		LocalDate dateOfBirth = LocalDate.of(2010, 9, 26);
		MonthDay birthday = MonthDay.of(dateOfBirth.getMonth(), dateOfBirth.getDayOfMonth());
		MonthDay currentMonthDay = MonthDay.from(LocalDate.now());

//		System.out.println(Month.from(LocalDate.now()).getValue());

		if (currentMonthDay.equals(birthday)) {
			System.out.println("Many Many happy returns of the day !!");
		} else {
			System.out.println("Sorry, today is not your birthday");
		}
	}

	public static void test3() {
		// 加一周
		LocalDate today = LocalDate.now();
		LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
		System.out.println("Today is : " + today);
		System.out.println("Date after 1 week : " + nextWeek);

		LocalDate previousYear = today.minus(1, ChronoUnit.YEARS);
		System.out.println("Date before 1 year : " + previousYear);

		LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
		System.out.println("Date after 1 year : " + nextYear);

		System.out.println(LocalDate.of(2012, 6, 30).isBefore(LocalDate.of(2012, 6, 30)));
		System.out.println(LocalDate.of(2012, 6, 30).isAfter(LocalDate.of(2012, 6, 30)));
		System.out.println(LocalDate.of(2012, 6, 30).isEqual(LocalDate.of(2012, 6, 30)));

		// 计算两个日期之间的天数和月数
		LocalDate java8Release = LocalDate.of(2014, 10, 14);
		Period period = Period.between(java8Release, today);
		System.out.println("Months left between today and Java 8 release : " + (period.getYears() * 12 + period.getMonths()));
		System.out.println("Months left between today and Java 8 release : " + period.getDays());

		// 时间戳
		long now = System.currentTimeMillis();
		Instant timestamp = Instant.now();
		System.out.println("What is value of this instant " + timestamp + "," + timestamp.toEpochMilli() + "," + System.currentTimeMillis());
		System.out.println(timestamp.toEpochMilli());
		System.out.println(now);

		String dayAfterTommorrow = "20140116";
		LocalDate formatted = LocalDate.parse(dayAfterTommorrow, DateTimeFormatter.BASIC_ISO_DATE);
		System.out.printf("Date generated from String %s is %s %n", dayAfterTommorrow, formatted);

		String goodFriday = "04 18 2014";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy");
		LocalDate holiday = LocalDate.parse(goodFriday, formatter);
		String strFormat = DateTimeFormatter.ofPattern("MMM dd yyyy").format(holiday);
		System.out.printf("Successfully parsed String %s, date is %s%n", strFormat, holiday);

	}

	public static void test4() {
		LocalDate a = LocalDate.of(2016, 9, 28);
		LocalDate b = LocalDate.of(2018, 7, 1);
		// 此处暂未明白
		System.out.println(a.adjustInto(b));

		System.out.println(b.atStartOfDay());
		System.out.println("atTime:" + a.atTime(18, 23));
		System.out.println("format:" + a.format(DateTimeFormatter.ISO_LOCAL_DATE)); // 格式化
		System.out.println("from:" + LocalDate.from(LocalDateTime.of(2015, 3, 31, 15, 0)));// 抽取生成对象
		System.out.println("get:" + a.get(ChronoField.DAY_OF_WEEK));
		System.out.println("getChronology:" + a.getChronology());// 当前使用的年历(注:可以定制年历)
		System.out.println("getDayOfWeek:" + a.getDayOfWeek().getValue());
		System.out.println("getDayOfYear:" + a.getDayOfYear());
		System.out.println("getEra:" + a.getEra().getValue()); // 时间，纪元
		System.out.println("getLong:" + a.getLong(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH));
		System.out.println("getMonth:" + a.getMonth());
		System.out.println("isSupported:" + a.isSupported(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH));
		System.out.println("isSupported:" + a.isSupported(ChronoUnit.HOURS));
		System.out.println("lengthOfMonth:" + a.lengthOfMonth());
		System.out.println("lengthOfYear:" + a.lengthOfYear());
		System.out.println("query:" + a.query(TemporalQueries.precision()));// ?
		System.out.println("range:" + a.range(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH));// ?
	}
}
