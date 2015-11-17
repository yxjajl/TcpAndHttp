package com.hook;

public class TraditionalThead {
	private Thread shutdownHook;

	public static void main(String[] args) {
		TraditionalThead b = new TraditionalThead();
		b.addShutdownHook();

		try {
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	public void addShutdownHook() {
		shutdownHook = new Thread("BootStrapShutdownHook") {
			public void run() {
				System.out.println("ShutdownHook Executed...");
			}
		};
		Runtime.getRuntime().addShutdownHook(shutdownHook);
	}
}