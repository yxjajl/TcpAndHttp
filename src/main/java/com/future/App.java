package com.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {
	ExecutorService executor = Executors.newFixedThreadPool(10);

	ArchiveSearcher searcher = null;

	void showSearch(final String target) throws InterruptedException {
		Future<String> future = executor.submit(new Callable<String>() {
			public String call() {
				return searcher.search(target);
			}
		});
		System.out.println("displayOtherThings");// do other things while searching
		try {
			System.out.println(future.get()); // use future
		} catch (ExecutionException ex) {
			return;
		}
	}
}