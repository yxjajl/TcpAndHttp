package com.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Lists;

public class ListNewTest {

	public static void main(String[] args) {
		test5();
	}

	public static void test1() {
		List<Integer> ints = Lists.newArrayList(1, 2, 3, 4, 5, 5);
		// System.out.println("ints sum is:" + ints.stream().reduce(0, (sum, item) -> sum + item));
		System.out.println("ints sum is:" + ints.stream().parallel().reduce(0, (sum, item) -> sum + item, (sum, item) -> sum - item));
	}

	public static void test2() {
		List<String> names = Arrays.asList("a", "b", "abc", "e", "123");
		names.stream().filter(name -> name.length() == 3).map(name -> name.toUpperCase()).forEach(System.out::println);
		names.stream().forEach(System.out::println);
		System.out.println("index:" + names.indexOf("e"));

		int[] intArr = { 0 };
		Map<Integer, String> toMap2 = names.stream().collect(Collectors.toMap(name -> intArr[0]++, name -> name, (a, b) -> a + b));
		toMap2.forEach((k, v) -> System.out.println(k + "," + v));

		// toMap2 = new HashMap<Integer, String>();
		// for(int i =0;i < names.size(); i++) {
		// toMap2.put(i, names.get(i));
		// }
		// toMap2.forEach((k, v) -> System.out.println(k + "," + v));
	}

	public static void test4() {
		List<Person> personList = getPersonList();
		Map<Integer, List<Person>> map = personList.stream().collect(Collectors.groupingBy(Person::getAge));
		map.forEach((k, v) -> v.forEach(p -> System.out.println(p.getName())));
	}

	public static void test3() {
		List<Person> personList = getPersonList();
		HashMap<String, HashMap<Integer, List<Person>>> muMap =

		personList.stream().collect(Collectors.groupingBy(Person::getCity, HashMap<String, HashMap<Integer, List<Person>>>::new,
				Collectors.groupingBy(Person::getAge, HashMap<Integer, List<Person>>::new, Collectors.toList())));

		// muMap.keySet().forEach(System.out::println);
		// muMap.values().forEach(map -> {
		// map.keySet().forEach(System.out::println);
		// });

		muMap.forEach((k, v) -> {
			System.out.println("========" + k + "=======");
			v.forEach((subk, subv) -> {
				System.out.println("<" + subk + ">");
				subv.forEach(p -> System.out.println(p.getId()));
			});
		});
	}

	public static void test5() {
		Stream.of("one", "two", "three", "four").filter(e -> e.length() > 3).peek(e -> System.out.println("Filtered value: " + e)).map(String::toUpperCase)
				.peek(e -> System.out.println("Mapped value: " + e)).collect(Collectors.toList()).forEach(System.out::println);

		System.out.println("=================");
		List<Person> personList = getPersonList();
		personList.stream().peek(e -> System.out.println("a:" + e.getName())).count();

		// Random.ints().limit(5);
		// Stream.iterate(10, n -> n + 3).limit(5).forEach(System.out::println);
		// Stream.iterate(0, n -> new java.util.Random().nextInt(5)).limit(5).forEach(System.out::println);

		Supplier<Integer> random = () -> new java.util.Random().nextInt(5);
		Stream.generate(random).limit(5).forEach(System.out::println);
	}

	public static List<Person> getPersonList() {
		List<Person> people = new ArrayList<Person>();

		Person p = new Person();
		p.setId(1);
		p.setName("rick");
		p.setCity("sz");
		p.setAge(11);
		people.add(p);

		p = new Person();
		p.setId(2);
		p.setName("wang");
		p.setCity("sz");
		p.setAge(31);
		people.add(p);

		p = new Person();
		p.setId(5);
		p.setName("rick5");
		p.setCity("sz");
		p.setAge(11);
		people.add(p);

		p = new Person();
		p.setId(3);
		p.setName("li");
		p.setCity("sh");
		p.setAge(60);
		people.add(p);

		p = new Person();
		p.setId(4);
		p.setName("h4");
		p.setCity("sh");
		p.setAge(60);
		people.add(p);

		return people;
	}

}
