package com.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class HashTest {

	public static void main(String[] args) {
		HashSet<Integer> hash = new HashSet<Integer>();
		Random random = new Random();
		while (hash.size() != 6) {
			int randomValue = random.nextInt(33) + 1;
			hash.add(randomValue);
		}
		String result = "结果是:";
		for (Integer i : hash) {
			result += i + " ";
		}
		System.out.println(result);

		Iterator ir = hash.iterator();
		while (ir.hasNext()) {
			System.out.println(ir.next());
		}
	}
}