package com.callback;

import java.util.function.Consumer;

public class MyFetcher implements Fetcher {

	final Data data;

	public MyFetcher(Data data) {
		this.data = data;
	}

	@Override
	public void fetchData(FetcherCallback callback) {
		try {
			callback.onData(data);
		} catch (Exception e) {
			callback.onError(e);
		}
	}

	@Override
	public void fetchData(Consumer<Data> onData, Consumer<Throwable> onError) {
		try {
			onData.accept(data);
		} catch (Exception e) {
			onError.accept(e);
		}

	}

}
