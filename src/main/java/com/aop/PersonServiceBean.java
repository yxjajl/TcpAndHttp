package com.aop;

public class PersonServiceBean implements PersonService {
	private String user = null;

	public PersonServiceBean() {
		super();
	}

	public PersonServiceBean(String user) {
		super();
		this.user = user;
	}

	public String getPersonName(Integer personId) {
		System.out.println("我是getPersonName()方法");
		return "get";
	}

	public void save(String name) {
		System.out.println("我是save()方法");
	}

	public void update(String name, Integer personId) {
		System.out.println("我是update()方法");
	}

	public void exception() throws Exception {
		System.out.println("exception method ");
		throw new Exception("error ");
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}