package com.annotation.springlock;

import org.springframework.stereotype.Component;


@OrderQuery(platform = { "a", "b" })
@Component
public class AOrderQuery {
	public void say() {
		System.out.println("abc=====================");
	}
}
