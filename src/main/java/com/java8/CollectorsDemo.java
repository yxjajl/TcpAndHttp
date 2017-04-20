package com.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectorsDemo {
	public static void main(String[] args) {
		// Accumulate names into a List
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
		p.setCity("bj");
		p.setAge(31);
		people.add(p);

		p = new Person();
		p.setId(1);
		p.setName("li");
		p.setCity("sh");
		p.setAge(60);
		people.add(p);

		List<String> list = people.stream().map(Person::getName).collect(Collectors.toList());
		System.out.println(list);

		String joined = people.stream().map(Person::getName).collect(Collectors.joining("#", ",", "]"));
		System.out.println(joined);

		int total = people.stream().collect(Collectors.summingInt(Person::getId));
		System.out.println(total);

		double average = people.stream().collect(Collectors.averagingDouble(Person::getAge));
		System.out.println("average:" + average);

		Function<Person, Person> keyFunction = it ->{ it.setCity(it.getCity().toUpperCase());return it;};
		Map<String, List<Person>> byDept = people.stream().map(keyFunction).collect(Collectors.groupingBy(Person::getCity));
		System.out.println("byDept:" + byDept);
		
		Map<String, List<String>> byDept2 = people.stream().collect(Collectors.groupingBy(Person::getCity,Collectors.mapping(Person::getName, Collectors.toList())));
		System.out.println("byDept2:" + byDept2);
		
		Map mmm = people.stream().collect(Collectors.groupingBy(Person::getId, TreeMap::new, Collectors.mapping(Person::getName, Collectors.toList())));
		System.out.println("MMM+" + mmm);
		
		System.out.println("mapping = "+people.stream().collect(Collectors.mapping(Person::getName, Collectors.toList())));

		// Map<Integer, String> toMap =
		// people.stream().collect(Collectors.toMap(Person::getId,
		// Person::getName));
		// System.out.println(toMap);

		Map<Integer, String> toMap2 = people.stream().collect(Collectors.toMap(Person::getId, Person::getName, (a, b) -> a + "," + b));
		System.out.println("toMap2:" + toMap2);
		Map<Integer, String> toMap3 = people.stream().collect(Collectors.toMap(Person::getId, Person::getName, (a, b) -> a));
		System.out.println("toMap3:" + toMap3);
		System.out.println("max:" + people.stream().collect(Collectors.maxBy((a, b) -> a.getId() - b.getId())).get().getId());

		StringJoiner sj = new StringJoiner(":", "[", "]");
		sj.setEmptyValue("Empty").add("George").add("Sally").add("").add("Fred").add(null).add("");
		sj.add("hello").add("world").add("rick");
		System.out.println("sj = " + sj);

		StringJoiner sj2 = new StringJoiner(",", "(", ")");
		StringJoiner sj3 = sj2.merge(sj);
		System.out.println("sj3 = " + sj3);

		int[] arr = { 8, 9, 13, 14, 15, 21 };
		Arrays.stream(arr).forEach(System.out::println);
		System.out.println("下标:" + Arrays.binarySearch(arr, 8));

		Person[] dd = people.toArray(new Person[0]);
		Arrays.sort(dd, (a, b) -> a.getId() - b.getId());
		Person key = new Person();
		key.setId(3);
		System.out.println("下标Integer :" + Arrays.binarySearch(dd, key, (a, b) -> a.getId() - b.getId()));

	}
}

class Person {
	private int id;
	private String name;
	private String city;
	private int age;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
