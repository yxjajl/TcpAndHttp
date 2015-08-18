package com.file;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class SortMapUtil {
	public static <K, V extends Comparable<? super V>> void sort(SortedMap<K, V> map) {
		// Set<Entry<K, V>> set = new TreeSet<Entry<K, V>>(new
		// Comparator<Entry<K, V>>() {
		// public int compare(Entry<K, V> o1, Entry<K, V> o2) {
		// return (o1.getValue()).compareTo(o2.getValue());
		// }
		// });
		// for (Entry<K, V> entry : map.entrySet()) {
		// set.add(entry);
		// }

		// for (Entry<K, V> entry : set) {
		// System.out.println(entry.getKey() + "," + entry.getValue());
		// }

		List<Entry<K, V>> list = new ArrayList<Entry<K, V>>();
		list.addAll(map.entrySet());

		// Collections.sort(list,Comparator.comparing(Comparator::compareTo));
		Collections.sort(list, (o1, o2) -> {
			return o1.getValue().compareTo(o2.getValue());
		});

		list.forEach((entry) -> System.out.println(entry.getKey() + "," + entry.getValue()));

		// map.clear();

		// for (Entry<K, V> entry : set) {
		// map.put(entry.getKey(), entry.getValue());
		// }
	}

	public static <K extends Number, V extends Number> Number add(K k, V v) {
		return k.doubleValue() + v.doubleValue();
	}

	public static void main(String[] args) {
		// System.out.println(SortMapUtil.add(3, 2.0));
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		Set<Entry<String, Integer>> set = new TreeSet<Entry<String, Integer>>(Comparator.comparing(Entry<String, Integer>::getValue));
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			int r = random.nextInt(100);
			map.put(String.valueOf(i), r);
		}
		sort(map);
		System.out.println("==========================================");
		
		map.entrySet().forEach(entry->{System.out.println(entry.getKey() + "," + entry.getValue());});
	}
}
