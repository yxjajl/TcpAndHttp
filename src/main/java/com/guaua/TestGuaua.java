package com.guaua;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.CharMatcher;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Joiner.MapJoiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Range;

public class TestGuaua {
	public static void demo1() {
		String delimiter = ",";
		// Joiner类一旦创建则不可变，满足不可变性，因此线程安全
		Joiner joiner = Joiner.on(delimiter);
		List<String> langs = Lists.newArrayList();
		langs.add("a");
		langs.add("");
		langs.add("b");
		langs.add(null);
		langs.add("c");
		// List<List<String>> subList = Lists.partition(langs, 2);

		// 忽略null

		String excludeNullString = joiner.skipNulls().join(langs);
		// 将null替代为empty字符串
		String replaceNullString = joiner.useForNull("empty").join(langs);
		System.out.println("excludeNullString: " + excludeNullString);
		System.out.println("replaceNullString: " + replaceNullString);

		// 不对null处理，默认会抛NullPointerException
		String defaultNullString = joiner.skipNulls().join(langs); // langs为List<String>
		System.out.println("defaultNullString: " + defaultNullString);

	}

	public static void demo2() {
		String delimiter = "&";
		// Joiner类一旦创建则不可变，满足不可变性，因此线程安全
		Joiner joiner = Joiner.on(delimiter).skipNulls();
		// joiner.useForNull("empty"); //重复定义null操作会抛异常
		String res = joiner.join(null, "foo", "bar");
		System.out.println(res); // foo,bar

	}

	public static void demo3() throws IOException {
		// append到StringBuilder
		StringBuilder stringBuilder = new StringBuilder();
		Joiner joiner = Joiner.on(",").skipNulls();
		joiner.appendTo(stringBuilder, "appendTo", "StringBuilder");
		joiner.appendTo(stringBuilder, "appendTo", "StringBuilder");
		System.out.println(stringBuilder); // appendTo,StringBuilder
		// append到输出流
		// FileWriter writer = new FileWriter("append_text.txt");
		// joiner.appendTo(writer, "appendTo", "FileWriter");
		// writer.close();

		Map<String, String> map = new HashMap<>();
		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("key3", "value3");
		MapJoiner mapJoiner = Joiner.on(",").withKeyValueSeparator(":");
		String str = mapJoiner.join(map);
		System.out.println(str);

	}

	public static void demo4() {
		String str = "try ,do , your , best,";
		Splitter splitter = Splitter.on(",").trimResults(); // 用逗号分割且去掉每个字符串周围的空格
		// splitter.trimResults(); //这样是不会去掉各个元素空格的, 它仅返回一个新的Splitter
		Iterable<String> res = splitter.split(str);
		for (String temp : res)
			System.out.println("=" + temp);

		String[] arr = str.split(str);
		for (String temp : arr)
			System.out.println("=" + temp);
	}

	public static void demo5() {
		// 用padChar填充string后面, 使string最小长度为minLength
		System.out.println(Strings.padEnd("1", 3, '0'));
		// 用padChar填充string前面, 使string最小长度为minLength
		System.out.println(Strings.padStart("1", 3, '0'));
		// null转换为""
		System.out.println(Strings.nullToEmpty(null));
		// ""转换为null
		System.out.println(Strings.emptyToNull(""));
		// true, 如果字符串为null或者""
		System.out.println(Strings.isNullOrEmpty(""));
		System.out.println(Strings.repeat("1", 3));

	}

	public static void demo6() {
		String source = "a1b2c3";
		CharMatcher matcher = CharMatcher.DIGIT; // 预定义的 DIGIT 类型
		System.out.println(matcher.matches('8'));
		System.out.println(matcher.retainFrom(source));
		System.out.println(matcher.countIn(source));
		System.out.println(matcher.removeFrom(source));
		System.out.println(matcher.trimFrom("1a2b3c4"));
	}

	public static void demo7() {
		String name = "abc";
		System.out.println(Preconditions.checkNotNull(name, "检查对象不能为空"));
		System.out.println(Preconditions.checkElementIndex(2, name.length(), "检查数组索引"));
		Preconditions.checkArgument(name.length() > 100, "检查参数值");
		// System.out.println(Preconditions.checkState(validateObjectState(),
		// "检查对象状态"));

	}

	public static void demo8() {
		// Range<Integer> ageRange = Range.closed(35, 50);
		// // Person到age转换的function
		// Function<Person, Integer> ageFunction = new Function<Person,
		// Integer>() {
		// @Override
		// public Integer apply(Person person) {
		// return person.getAge();
		// }
		// };
		// // 这样得到年龄再[35, 50]之间的人
		// Predicate<Person> predicate = Predicates.compose(ageRange,
		// ageFunction);

	}

	public static void main(String[] args) throws Exception {
		demo8();
	}
}
