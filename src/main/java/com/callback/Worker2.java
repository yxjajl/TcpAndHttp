package com.callback;

public class Worker2 {

	public void doWork() {
		Fetcher fetcher = new MyFetcher(new Data(1, 0));
		fetcher.fetchData(Data::toString, e -> onError(e));
	}

	public static void main(String[] args) {
		Worker2 w = new Worker2();
		w.doWork();
	}

	public void onError(Throwable cause) {
		System.out.println("An error accour: " + cause.getMessage());
	}
}
