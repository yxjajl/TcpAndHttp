package com.thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class PhaserTest {

	public static void main(String[] args) {
		Phaser phaser = new Phaser(3);
		ExecutorService executor = Executors.newFixedThreadPool(3);
		executor.submit(new Thread(new PhaserTestRunner(phaser, "1号选手")));
		executor.submit(new Thread(new PhaserTestRunner(phaser, "2号选手")));
		executor.submit(new Thread(new PhaserTestRunner(phaser, "3号选手")));
		executor.shutdown();
	}

}

class PhaserTestRunner implements Runnable {
	public Phaser phaser;
	public String name;

	public PhaserTestRunner(Phaser phaser, String name) {
		this.phaser = phaser;
		this.name = name;
	}

	public void run() {
		try {
			Thread.sleep(1000 * (new Random()).nextInt(8));
			System.out.println(name + " 准备好了...");
			// barrier的await方法，在所有参与者都已经在此 barrier 上调用 await 方法之前，将一直等待。
			phaser.arriveAndAwaitAdvance();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(name + " 起跑！");
	}
}
