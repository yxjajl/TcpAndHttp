package com.observer;

public class Test {

	public static void main(String[] args) {

		// 创建被观察者对象
		Watched watched = new Watched();
		// 创建观察者对象，并将被观察者对象登记
		Watcher watcher1 = new Watcher(watched);
		Watcher watcher2 = new Watcher(watched);
		// 给被观察者状态赋值
		watched.setData("start");
		watched.setData("run");
		watched.setData("stop");

	}

}