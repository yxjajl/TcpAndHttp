package com.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class HashTest {

	public static void main(String[] args) {
		HashSet<String> hash = new HashSet<>();
		
		hash.add("3");
		hash.add("3");
		hash.add(null);
		hash.add(null);
		
		for(int i= 0;i < 5; i++)
		hash.add(""+i);
		
		Iterator ir = hash.iterator();
		while (ir.hasNext()) {
			System.out.println(ir.next());
		}
	}
}