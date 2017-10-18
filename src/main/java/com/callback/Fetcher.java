package com.callback;

import java.util.function.Consumer;

public interface Fetcher {
	void fetchData(FetcherCallback callback);

	public void fetchData(Consumer<Data> onData, Consumer<Throwable> onError);
	
	
	
}
