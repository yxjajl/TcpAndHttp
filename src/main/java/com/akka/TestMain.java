package com.akka;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int count = 0;
		for(int i=0;i <=48; i++) {
			count += i;
		}
		
		System.out.println(count/60);
		System.out.println(count%60);
	}

}
