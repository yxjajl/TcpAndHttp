package com.annotation.springlock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class TestService {
	private final static String lock = "123";
	@Autowired
	@Car
	private VehicleInerface vehicleInerface;

	@Dlock(lock)
	public void aa(String str) {
		System.out.println("========aa=======" + str);
		vehicleInerface.dodo();
	}
}
