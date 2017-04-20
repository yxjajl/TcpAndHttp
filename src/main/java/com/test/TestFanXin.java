package com.test;

import java.util.ArrayList;
import java.util.List;

//请记住PECS原则：生产者（Producer）使用extends，消费者（Consumer）使用super

//1) 参数写成：T<? super B>，对于这个泛型，?代表容器里的元素类型，由于只规定了元素必须是B的超类，导致元素没有明确统一
//的“根”（除了Object），所以这个泛型你其实无法使用它（除了把元素强制转成Object）。所以只能插入操作，而无法读

//2) 参数写成： T<? extends B>，由于指定了所有元素的“根”是B，你任何时候都可以安全的用B来使用容器里的元素，但是由于以B
//为祖先的子树有很多，不同子树不兼容，所以禁止做插入操作，只做读取

class TestFanXin {

	public static void main(String[] args) {
		int i = 0;
		System.out.println(i++ + ++i);
		print(new Integer(3));
	}

	public static void test2() {
		List<? extends FB> list = new ArrayList<>();
//		list.add(new FA()); //不能add
//		list.add(new FB()); //不能add
//		list.add(new FC());//不能add

		FA fa = list.get(0);
		FB fb = list.get(1);
//		FC fc = list.get(2);不能get
	}

	public static void test1() {
		List<? super FB> list = new ArrayList<>();
//		list.add(new FA()); //不能add 比 FB大
		list.add(new FB());
		list.add(new FC());

//		FA fa = list.get(0); // 不能get
//		FB fb = list.get(1);// 不能get
//		FC fc = list.get(2);// 不能get
	}

	public static <T extends Integer> void print(T t) {
		System.out.println(t);
	}
}

class FA {
	//
}

class FB extends FA {
	//
}

class FC extends FB {
	//
}