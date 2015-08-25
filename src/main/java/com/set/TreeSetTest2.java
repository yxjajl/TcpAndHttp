package com.set;

import java.util.Random;
import java.util.TreeSet;

public class TreeSetTest2 {
	  public static void main(String[] args)
	     {
	         TreeSet<Integer> tree = new TreeSet<Integer>();
	         Random random = new Random();
	         while(tree.size()!= 6){
	          int randomValue = random.nextInt(33) + 1;
	          tree.add(randomValue);
	         }
	         String result = "结果是:";
	         for(Integer i: tree){
	          result += i + " "; 
	         }
	        System.out.println(result);
	     }
}
