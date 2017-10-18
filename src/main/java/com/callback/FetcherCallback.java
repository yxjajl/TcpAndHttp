package com.callback;

public interface FetcherCallback {
	default void onData(Data data) throws Exception {
		System.out.println("Data received: " + data);
	}
	default void onError(Throwable cause) {
		System.out.println("An error accour: " + cause.getMessage());
	}
}
