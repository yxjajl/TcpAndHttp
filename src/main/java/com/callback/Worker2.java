package com.callback;

public class Worker2 {

	public void doWork() {
		Fetcher fetcher = new MyFetcher(new Data(1, 0));
		fetcher.fetchData(Data::toString, Worker2::onError);
	}

	public static void main(String[] args) {
		Worker2 w = new Worker2();
		w.doWork();
	}

	public static void onError(Throwable cause) {
		System.out.println("An error accour: " + cause.getMessage());
	}
}
