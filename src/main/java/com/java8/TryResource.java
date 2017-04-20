package com.java8;

import java.io.Closeable;
import java.io.IOException;

public class TryResource {
	public static void main(String[] args) {
		try (NewResource res = new NewResource("Res1 closing");) {
			res.doSomeWork();
		} catch (Exception e) {
			e.printStackTrace();
		}

		close(new NewResource("Res1 closing"));
	}

	public static <T extends AutoCloseable> void close(T t) {
		if (t == null) {
			return;
		}
		try {
			t.close();
		} catch (Exception e) {

		}
	}
}

class NewResource implements Closeable {
	private String name;

	public NewResource(String name) {
		this.name = name;
	}

	public void doSomeWork() {
		System.out.println("dosomework");
	}

	@Override
	public void close() throws IOException {
		System.out.println("NewResource " + name + " close ");
	}

}
