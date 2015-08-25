package com.lock;

public class TestThread implements Runnable {

	public Business business;
	public String name;

	@Override
	public void run() {
		for (int i = 1; i < 100; i++) {
			try {
				business.changeValue(name);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
