package com.annotation;

public class SelfServiceImp implements SelfService {
	private static final String Lock = "abc";

	@Self(Lock)
	public void syncMethod() {
		System.out.println("syncMethod==start");
		try {
			Thread.sleep(10000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("syncMethod==end");
	}

}
