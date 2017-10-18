package com.callback;

public class Worker {

	public void doWork() {
		Fetcher fetcher = new MyFetcher(new Data(1, 0));
		fetcher.fetchData(new FetcherCallback(){});
	}

	public static void main(String[] args) {
		Worker w = new Worker();
		w.doWork();
	}

}
