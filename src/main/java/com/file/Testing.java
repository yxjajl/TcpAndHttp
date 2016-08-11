package com.file;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Testing {

	public static void main(String[] args) {

		HashMap<String, Double> map = new HashMap<String, Double>();
		TreeMap<String, Double> sorted_map = new TreeMap<String, Double>(new ValueComparator(map));

		map.put("A", 100.5);
		map.put("B", 10.4);
		map.put("C", 80.4);
		map.put("D", 60.3);

		System.out.println("unsorted map: " + map);

		sorted_map.putAll(map);

		System.out.println("results: " + sorted_map);
	}
}

class ValueComparator implements Comparator<String> {

	Map<String, Double> base;

	public ValueComparator(Map<String, Double> base) {
		this.base = base;
	}

	// Note: this comparator imposes orderings that are inconsistent with
	// equals.
	public int compare(String a, String b) {
		if (base.get(a) >= base.get(b)) {
			return -1;
		} else {
			return 1;
		} // returning 0 would merge keys
	}
}
